package com.anastasia.smart_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SmartServiceConfig {


    @Bean("virtualExecutorService")
    public ExecutorService virtualExecutorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean("scheduler")
    public ScheduledExecutorService scheduler() {
        return Executors.newScheduledThreadPool(5);
    }
}
