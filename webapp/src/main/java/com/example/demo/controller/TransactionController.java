package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private User getAuthenticationUser(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName());
    }

    @GetMapping
    public List<Transaction> get(Authentication authentication) {
        return transactionRepository.findByUser(getAuthenticationUser(authentication));
    }

    @PostMapping
    public Transaction post(@RequestBody Transaction transaction, Authentication authentication) {
        transaction.setUser(getAuthenticationUser(authentication));
        transactionRepository.save(transaction);
        return transaction;
    }

    @PutMapping("/{id}")
    public Transaction put(@RequestBody Transaction transaction, @PathVariable Long id, Authentication authentication) {
        User user = getAuthenticationUser(authentication);
        Transaction oldTransaction = transactionRepository.findByIdAndUser(id, user);
        if (oldTransaction == null) {
            return null;
        }
        transaction.setId(id);
        transaction.setUser(user);
        transactionRepository.save(transaction);
        return transaction;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication authentication) {
        User user = getAuthenticationUser(authentication);
        Transaction transaction = transactionRepository.findByIdAndUser(id, user);
        if (transaction != null) {
            transactionRepository.delete(transaction);
        }
    }
}
