package com.example.demo.repository;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    List<Attachment> findByTransaction(Transaction transaction);
    Attachment findByIdAndTransaction(UUID id, Transaction transaction);
}
