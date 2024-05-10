package com.capgemini.wsb.fitnesstracker.livecoding.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@EnableScheduling
@Component
@Slf4j
public class MySpringScheduler {

    @Scheduled(fixedRate = 5000)
    public void scheduleTask1() {
        log.info("Scheduled task: 1");
    }

    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    public void scheduleTask2() {
        log.info("Scheduled task: 2");
    }

}
