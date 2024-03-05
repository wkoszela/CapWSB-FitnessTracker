package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("BeanCycle")
public class MyBean {

    public MyBean () {
        System.out.println("Instantiation");
    }

    @PostConstruct
    public void init(){
        System.out.println("Initializing..");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroying..");
    }
}

