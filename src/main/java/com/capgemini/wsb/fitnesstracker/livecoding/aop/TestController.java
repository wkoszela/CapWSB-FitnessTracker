package com.capgemini.wsb.fitnesstracker.livecoding.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/test")
@Slf4j
public class TestController {

    @GetMapping
    public String getName() {
        log.info("getName() called");
        return "John";
    }

    @GetMapping("/surname")
    public String getSurname() {
        log.info("getSurname() called");
        return "Kowalski";
    }

}
