package com.example.demo.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.route53.AmazonRoute53ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class AmazonClient {

    private AmazonS3 s3Client;

    private AmazonSNS snsClient;

    private String awsBucketName;

    @Value("${accountNumber}")
    String accountNumber;

    @Value("${awsAccessKeyId}")
    private String awsAccessKeyId;

    @Value("${awsSecretKey}")
    private String awsSecretKey;

    @PostConstruct
    public void init() {
        String region = "us-east-1";
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(this.awsAccessKeyId, this.awsSecretKey);
        String domain = AmazonRoute53ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build().listHostedZones().getHostedZones().get(0).getName();
        domain = domain.substring(0, domain.length() - 1);
        this.awsBucketName = domain + ".csye6225.com";
        this.s3Client = AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
        this.snsClient = AmazonSNSClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
    }

    public String uploadFile(File file, String fileName) {
        s3Client.putObject(new PutObjectRequest(this.awsBucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
        return "https://s3.us-east-1.amazonaws.com/" + this.awsBucketName + "/" + fileName;
    }

    public String deleteFile(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        s3Client.deleteObject(new DeleteObjectRequest(this.awsBucketName, fileName));
        return fileName;
    }

    public void publish(String email) {
        snsClient.publish(new PublishRequest("arn:aws:sns:us-east-1:" + accountNumber + ":password_reset", email));
    }
}