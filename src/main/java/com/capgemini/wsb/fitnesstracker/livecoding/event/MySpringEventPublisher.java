package com.capgemini.wsb.fitnesstracker.livecoding.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class MySpringEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishEvent(final String message) {
        log.info("Publishing custom event. ");
        MySpringEvent mySpringEvent = new MySpringEvent(this, message);
        eventPublisher.publishEvent(mySpringEvent);
        log.info("Custom event published. ");
    }

}
