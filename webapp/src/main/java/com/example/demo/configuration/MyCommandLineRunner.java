package com.example.demo.configuration;

import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setEmail("root");
//        user.setUsername(user.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode("root"));
//        userRepository.save(user);
//        Transaction transaction = new Transaction();
//        transaction.setDescription("coffee");
//        transaction.setMerchant("starbucks");
//        transaction.setAmount(2.69f);
//        transaction.setDate(new Date());
//        transaction.setCategory("food");
//        transaction.setUser(user);
//        transactionRepository.save(transaction);
    }
}