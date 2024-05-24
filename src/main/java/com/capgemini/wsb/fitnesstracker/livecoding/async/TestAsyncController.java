package com.capgemini.wsb.fitnesstracker.livecoding.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/async")
@Slf4j
@AllArgsConstructor
public class TestAsyncController {

    private final ServiceImpl service;

    @GetMapping
    public String dispatchTask() {
        log.info("Task is going to be dispatched");
        service.doSomething();
        return "Task dispatched";
    }

}
