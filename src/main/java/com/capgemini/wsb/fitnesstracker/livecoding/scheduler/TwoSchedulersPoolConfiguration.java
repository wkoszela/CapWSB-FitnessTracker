package com.capgemini.wsb.fitnesstracker.livecoding.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TwoSchedulersPoolConfiguration {

    @Bean(name = "mainSchedulerPool")
    @Primary
    public TaskScheduler mainScheduler() {
        final ThreadPoolTaskScheduler mainScheduler = new ThreadPoolTaskScheduler();
        mainScheduler.setPoolSize(10);
        mainScheduler.setThreadNamePrefix("Main-Scheduler");
        mainScheduler.initialize();
        return mainScheduler;
    }

    @Bean(name = "additionalSchedulerPool")
    public TaskScheduler additionalScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("Secondary-Scheduler");
        scheduler.initialize();
        return scheduler;
    }

}
