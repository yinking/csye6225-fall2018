package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<User> get() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public User post(@RequestBody User user, HttpServletResponse response) {
        String pattern = "[a-zA-Z0-9.]+@[a-zA-Z0-9]+(.[a-zA-Z0-9]+)+";
        if (Pattern.matches(pattern, user.getEmail())) {
            User existStudent = userRepository.findByEmail(user.getEmail());
            if (existStudent == null) {
                user.setUsername(user.getEmail());
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return user;
            }
        }
        response.setStatus(403);
        return null;
    }
}
