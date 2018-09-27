package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new Date());
        map.put("students", studentRepository.findAll());
        return map;
    }

    @PostMapping("/register")
    public String register(@RequestBody Student student) {
        Student existStudent = studentRepository.findByUsername(student.getUsername());
        if (existStudent == null) {
            student.setPassword(new BCryptPasswordEncoder().encode(student.getPassword()));
            studentRepository.save(student);
            return "success";
        } else {
            return "exist student";
        }
    }
}
