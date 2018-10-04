package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> get() {
        return userRepository.findAll();
    }

    @PostMapping
    public User post(@RequestBody User user) {
        user.setUsername(user.getEmail());
        User existStudent = userRepository.findByUsername(user.getUsername());
        if (existStudent == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }
        return null;
    }
}
