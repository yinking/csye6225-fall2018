package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void contextLoads() {
        for (int i = 1; i <= 100; i++) {
            Student student = new Student();
            student.setUsername("test" + i);
            student.setPassword(new BCryptPasswordEncoder().encode("test" + i));
            studentRepository.save(student);
        }
    }
}
