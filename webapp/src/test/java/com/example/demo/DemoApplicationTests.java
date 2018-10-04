package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserRepository studentRepository;

    @Test
    public void contextLoads() {
        for (int i = 1; i <= 100; i++) {
            User student = new User();
            student.setUsername("test" + i);
            student.setPassword(new BCryptPasswordEncoder().encode("test" + i));
            studentRepository.save(student);
        }
        for (int i = 1; i <= 100; i++) {
            User student = studentRepository.findByUsername("test"+i);
            assertNotNull(student);
        }
    }
}
