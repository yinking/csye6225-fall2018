package com.example.demo.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AmazonClient {

    private AmazonS3 s3Client;

    @Value("${awsBucketName}")
    private String bucketName;

    @Value("${awsAccessKeyId}")
    private String accessKeyId;

    @Value("${awsSecretKey}")
    private String secretKey;

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKeyId, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-1").withCredentials(new AWSStaticCredentialsProvider(creds)).build();
        return s3Client;
    }

    public String uploadFile(File file, String fileName) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
        return "https://s3.us-east-1.amazonaws.com/" + bucketName + "/" + fileName;
    }

    public String deleteFile(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return fileName;
    }
}