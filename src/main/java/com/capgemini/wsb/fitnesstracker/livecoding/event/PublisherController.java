package com.capgemini.wsb.fitnesstracker.livecoding.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publisher")
@RequiredArgsConstructor
class PublisherController {

    private final MySpringEventPublisher mySpringEventPublisher;

    @PostMapping
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public void publishEvent(@RequestParam String message) {

        mySpringEventPublisher.publishEvent(message);
    }

}
