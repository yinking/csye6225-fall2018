package com.example.demo.s3.services;

import java.io.File;

public interface S3Services {
    public void downloadFile(String keyName);

    public void uploadFile(String keyName, String uploadFilePath);


    public void uploadFile(String keyName, File file);

}
