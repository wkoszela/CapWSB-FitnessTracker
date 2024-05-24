package com.capgemini.wsb.fitnesstracker.livecoding.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceImpl {

    @Async("async-pool")
    public void doSomething() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Doing something..");
    }

}
