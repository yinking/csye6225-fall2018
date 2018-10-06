package com.example.demo.repository;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTransaction(Transaction transaction);
    Attachment findByIdAndTransaction(Long id, Transaction transaction);
}
