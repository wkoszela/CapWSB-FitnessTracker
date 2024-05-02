package com.capgemini.wsb.fitnesstracker.livecoding.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("BeanCycle")
@Service
public class MyBean {

    public MyBean() {
        System.out.println("Instantiation");
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing..");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying..");
    }
}
