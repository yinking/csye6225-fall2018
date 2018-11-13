package com.example.demo.controller;

import com.example.demo.configuration.AmazonClient;
import com.example.demo.entity.User;
import com.example.demo.exception.MyException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MyException myException;

    @GetMapping
    public List<User> get() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user, HttpServletResponse response) {
        String pattern = "[a-zA-Z0-9.]+@[a-zA-Z0-9]+(.[a-zA-Z0-9]+)+";
        if (Pattern.matches(pattern, user.getEmail())) {
            User existUser = userRepository.findByEmail(user.getEmail());
            if (existUser == null) {
                user.setUsername(user.getEmail());
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return user;
            }
        }
        myException.sendError(403, "User exist", response);
        return null;
    }

    @PostMapping("/reset")
    public void reset(@RequestBody Map<String, Object> payload, HttpServletResponse response) {
        String email = (String) payload.get("email");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            myException.sendError(403, "User not exist", response);
        } else {
            amazonClient.publish(email);
            System.out.println(email);
        }
    }
}
