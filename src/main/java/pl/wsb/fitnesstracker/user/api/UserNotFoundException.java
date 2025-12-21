package pl.wsb.fitnesstracker.user.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Wyjątek informujący o tym, że {@link User} nie został znaleziony.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    private UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Konstruktor wyjątku przyjmujący ID użytkownika.
     *
     * @param id identyfikator użytkownika
     */
    public UserNotFoundException(Long id) {
        this("Użytkownik o ID=%s nie został znaleziony".formatted(id));
    }

}
