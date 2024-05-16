package com.capgemini.wsb.fitnesstracker.livecoding.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "wlasna-metryka")
public class CopyrightEndpoint {

    @ReadOperation
    public String get() {
        return "All rights reserved";
    }

}
