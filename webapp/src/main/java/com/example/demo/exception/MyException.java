package com.example.demo.exception;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyException {
    public void sendError(int status, String message, HttpServletResponse response) {
        try {
            response.sendError(status, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
