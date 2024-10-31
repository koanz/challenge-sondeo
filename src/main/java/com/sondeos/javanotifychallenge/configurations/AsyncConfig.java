package com.sondeos.javanotifychallenge.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    /*@Bean(name = "notificationExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Hilos iniciales activos
        executor.setMaxPoolSize(10);  // Número máximo de hilos
        executor.setQueueCapacity(50); // Tamaño de la cola
        executor.setThreadNamePrefix("Notification-");
        executor.initialize();
        return executor;
    }*/
}
