package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public List<Student> index() {
        Student student1 = new Student();
        student1.setName("Han Luo");
        Student student2 = new Student();
        student2.setName("Ying Wang");
        Student student3 = new Student();
        student3.setName("Yu Fang");
        Student student4 = new Student();
        student4.setName("Zhiyong Zhang");
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        return studentRepository.findAll();
    }
    @PostMapping("/register")
    public void register() {
        Student student = new Student();
        student.setName("Zhiyong Zhang");
        studentRepository.save(student);
    }
}
