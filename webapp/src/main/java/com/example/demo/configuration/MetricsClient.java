package com.example.demo.configuration;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MetricsClient {

    private StatsDClient statsDClient;

    @PostConstruct
    public void metricsClient() {
        this.statsDClient = new NonBlockingStatsDClient("", "localhost", 8125);
    }

    public void incrementCounter(String key) {
        this.statsDClient.incrementCounter(key);
    }
}