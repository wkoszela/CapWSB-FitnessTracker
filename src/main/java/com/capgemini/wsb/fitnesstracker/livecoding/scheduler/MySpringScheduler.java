package com.capgemini.wsb.fitnesstracker.livecoding.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MySpringScheduler {

    public MySpringScheduler(@Qualifier("mainSchedulerPool") final TaskScheduler mainScheduler,
                             @Qualifier("additionalSchedulerPool") final TaskScheduler additionalScheduler) {
        this.mainScheduler = mainScheduler;
        this.additionalScheduler = additionalScheduler;
    }

    private final TaskScheduler mainScheduler;

    private final TaskScheduler additionalScheduler;

    /*
    @PostConstruct
    public void startScheduling() {

        mainScheduler.scheduleAtFixedRate(this::scheduleTask1, 5000);
        additionalScheduler.scheduleAtFixedRate(this::scheduleTask2, 3000);
        mainScheduler.scheduleWithFixedDelay(this::scheduleTask3, 5000);

    }
    */

    public void scheduleTask1() {
        log.info("Scheduled task: 1");
    }

    public void scheduleTask2() {
        log.info("Scheduled task: 2");
    }

    public void scheduleTask3() {
        log.info("Scheduled task: 3");
        try {
            log.info("Sleeping for 2 seconds");
            Thread.sleep(2000);
            log.info("Woke up");
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

    }

}
