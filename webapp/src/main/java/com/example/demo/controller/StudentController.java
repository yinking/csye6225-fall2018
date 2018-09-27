package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public List<Student> index() {
        return studentRepository.findAll();
    }

    @PostMapping("/register")
    public void register(@RequestBody Student student) {
        studentRepository.save(student);
    }
}
