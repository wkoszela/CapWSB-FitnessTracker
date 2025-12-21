package pl.wsb.fitnesstracker.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generyczny wyjątek dla błędów biznesowych.
 * Zostanie rozwiązany do {@link HttpStatus#BAD_REQUEST}, jeśli zostanie
 * obsłużony przez program obsługi wyjątków Springa.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    /**
     * Konstruktor wyjątku biznesowego.
     *
     * @param message wiadomość błędu
     */
    public BusinessException(String message) {
        super(message);
    }

}
