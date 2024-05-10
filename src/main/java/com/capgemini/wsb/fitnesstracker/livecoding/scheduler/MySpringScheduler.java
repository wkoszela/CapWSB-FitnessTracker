package com.capgemini.wsb.fitnesstracker.livecoding.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
public class MySpringScheduler {

    /*
    @Scheduled(fixedRate = 10000)
    public void scheduleTask1() {
        log.info("Scheduled task: 1");
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void scheduleTask2() {
        log.info("Scheduled task: 2");
    }
    */
    @Scheduled(cron = "* * * * * *")
    public void scheduleTask5() {
        log.info("Scheduled task: 5");
    }

}
    /*
    @Scheduled(fixedDelay = 3, timeUnit = TimeUnit.SECONDS)
    public void scheduleTask3() {
        try {
            log.info("Scheduled task: 3");
            log.info("Sleeping for 5 seconds");
            Thread.sleep(5000);
            log.info("Woke up");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    public void scheduleTask4() {
        try {
            log.info("Scheduled task: 4");
            log.info("Sleeping for 5 seconds");
            Thread.sleep(5000);
            log.info("Woke up");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    */


