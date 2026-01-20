package pl.wsb.fitnesstracker.user.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception rzucana gdy żądany użytkownik nie zostanie znaleziony w bazie danych.
 * <p>
 * Dziedziczy po {@link NotFoundException} i jest używana w sytuacjach,
 * gdy operacja wyszukiwania użytkownika po ID zwróci brak rezultatu.
 * </p>
 * <p>
 * Automatycznie formatuje wiadomość błędu z podanym ID użytkownika.
 * </p>
 *
 * @author Fitness Tracker Team
 * @see User
 * @see NotFoundException
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    private UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
