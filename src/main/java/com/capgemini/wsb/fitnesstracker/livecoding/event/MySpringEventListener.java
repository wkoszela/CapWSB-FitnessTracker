package com.capgemini.wsb.fitnesstracker.livecoding.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(final MySpringEvent event) {

        log.info("Received spring custom event in Listener - " + event.getMyMessage());
    }

}
