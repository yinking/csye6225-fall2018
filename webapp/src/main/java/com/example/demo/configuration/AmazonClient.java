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

    private String region = "us-east-1";

    @Value("${accountNumber}")
    String accountNumber;

    @Value("${awsAccessKeyId}")
    private String awsAccessKeyId;

    @Value("${awsSecretKey}")
    private String awsSecretKey;
//
//    @Value("${topicArn}")
//    private String topicArn;

    @PostConstruct
    public void init() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(this.awsAccessKeyId, this.awsSecretKey);
        String domain=AmazonRoute53ClientBuilder.standard().withRegion(this.region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build().listHostedZones().getHostedZones().get(0).getName();
        System.out.println(domain);
//        this.s3Client = AmazonS3ClientBuilder.standard().withRegion(this.region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
//        this.snsClient = AmazonSNSClientBuilder.standard().withRegion(this.region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
    }

    public String uploadFile(File file, String fileName) {
//        s3Client.putObject(new PutObjectRequest(awsBucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
//        return "https://s3.us-east-1.amazonaws.com/" + awsBucketName + "/" + fileName;
        return "";
    }

    public String deleteFile(String url) {
//        String fileName = url.substring(url.lastIndexOf("/") + 1);
//        s3Client.deleteObject(new DeleteObjectRequest(awsBucketName, fileName));
//        return fileName;
        return "";
    }

    public void publish(String email) {
        //snsClient.publish(new PublishRequest(topicArn, email));
    }
}