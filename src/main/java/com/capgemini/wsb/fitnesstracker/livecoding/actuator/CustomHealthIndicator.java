package com.capgemini.wsb.fitnesstracker.livecoding.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component

public class CustomHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;

    public CustomHealthIndicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Health health() {
        try {
            restTemplate.getForObject("https://google.com", String.class);
            return Health.up()
                         .build();
        } catch (Exception e) {
            return Health.down()
                         .withDetail("Error", e.getMessage())
                         .build();
        }
    }

}

