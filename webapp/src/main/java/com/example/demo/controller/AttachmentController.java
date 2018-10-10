package com.example.demo.controller;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.s3.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction/{id}/attachments")
public class AttachmentController {


    @Autowired
    S3Services s3Services;

//    @Value("${jsa.s3.uploadfile}")
//    private String uploadFilePath;

    @Value("${jsa.s3.key}")
    private String downloadKey;


//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("---------------- START UPLOAD FILE ----------------");
//        s3Services.uploadFile("a.py", uploadFilePath);
//        System.out.println("---------------- START DOWNLOAD FILE ----------------");
//        s3Services.downloadFile(downloadKey);
//    }


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //   /Users/wangying/Desktop/key
        factory.setLocation("/Users/wangying/ying/6225/a4/");
        return factory.createMultipartConfig();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    private Transaction getTransaction(Authentication authentication, UUID id) {
        User user = userRepository.findByUsername(authentication.getName());
        return transactionRepository.findByIdAndUser(id, user);
    }

    @GetMapping
    public List<Attachment> get(@PathVariable UUID id, Authentication authentication, HttpServletResponse response) {
        Transaction transaction = getTransaction(authentication, id);
        if (transaction == null) {
            response.setStatus(403);
            return null;
        }
        return attachmentRepository.findByTransaction(transaction);
    }

    @PostMapping
    public Attachment post(@RequestParam(value = "file") MultipartFile file, @PathVariable UUID id, Authentication authentication, HttpServletResponse response) {
        Transaction transaction = getTransaction(authentication, id);
        if (transaction == null) {
            response.setStatus(403);
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setTransaction(transaction);
        attachmentRepository.save(attachment);
        String fileName = file.getOriginalFilename();
        String url = attachment.getId() + fileName.substring(fileName.lastIndexOf("."));
        attachment.setUrl(url);
        attachmentRepository.save(attachment);
        try {
            File localFile=new File(url);
            file.transferTo(localFile);
            s3Services.uploadFile(url, multipartConfigElement().getLocation()+url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attachment;
    }


    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

    @PutMapping("/{idAttachments}")
    public Attachment put(@RequestParam(value = "file") MultipartFile file, @PathVariable UUID id, @PathVariable UUID idAttachments, Authentication authentication, HttpServletResponse response) {
        Transaction transaction = getTransaction(authentication, id);
        Attachment attachment = attachmentRepository.findByIdAndTransaction(idAttachments, transaction);
        if (attachment == null) {
            response.setStatus(403);
            return null;
        }
        new File(multipartConfigElement().getLocation() + attachment.getUrl()).delete();
        String fileName = file.getOriginalFilename();
        String url = attachment.getId() + fileName.substring(fileName.lastIndexOf("."));
        attachment.setUrl(url);
        attachmentRepository.save(attachment);
        try {
            file.transferTo(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @DeleteMapping("/{idAttachments}")
    public void delete(@PathVariable UUID id, @PathVariable UUID idAttachments, Authentication authentication, HttpServletResponse response) {
        Transaction transaction = getTransaction(authentication, id);
        Attachment attachment = attachmentRepository.findByIdAndTransaction(idAttachments, transaction);
        if (attachment == null) {
            response.setStatus(403);
        } else {
            attachmentRepository.delete(attachment);
            new File(multipartConfigElement().getLocation() + attachment.getUrl()).delete();
        }
    }
}
