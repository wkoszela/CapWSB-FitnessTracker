/**
 * The BusinessException class is a generic exception for business-related errors in a Java Spring
 * application that resolves to HttpStatus.BAD_REQUEST when handled by the Spring exception handler.
 */
package pl.wsb.fitnesstracker.exception.api;

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
