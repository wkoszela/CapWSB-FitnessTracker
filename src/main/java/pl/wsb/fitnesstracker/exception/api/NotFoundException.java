package pl.wsb.fitnesstracker.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generyczny wyjątek biznesowy informujący o tym, że zasób nie został
 * znaleziony.
 * Zostanie rozwiązany do {@link HttpStatus#NOT_FOUND}, jeśli zostanie obsłużony
 * przez program obsługi wyjątków Springa.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BusinessException {

    /**
     * Konstruktor wyjątku informującego o nieznalezieniu zasobu.
     *
     * @param message wiadomość błędu
     */
    public NotFoundException(String message) {
        super(message);
    }

}
