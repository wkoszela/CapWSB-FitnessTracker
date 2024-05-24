package com.capgemini.wsb.fitnesstracker.livecoding.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    @Value("${corepoolsize:2}")
    private int corePoolSize;

    @Value("${maxpoolsize:5}")
    private int maxPoolSize;

    @Value("${queuecapacity:0}")
    private int queueCapacity;

    @Value("${keepaliveseconds:30}")
    private int keepAliveSeconds;

    @Bean(name = "async-pool")
    public ThreadPoolTaskExecutor taskExecutor() {

        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("Thread-Async");
        executor.initialize();
        return executor;
    }

}