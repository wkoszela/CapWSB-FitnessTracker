package com.capgemini.wsb.fitnesstracker.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic exception for business related errors.
 * Will resolve to the {@link HttpStatus#BAD_REQUEST} if handled by the Spring's exception handler.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
