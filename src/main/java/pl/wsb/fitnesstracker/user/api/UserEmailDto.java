package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Rekord reprezentujący uproszczony DTO użytkownika,
 * zawierający tylko identyfikator i adres e-mail.
 * Służy do zwracania podstawowych informacji o użytkowniku,
 * np. w wynikach wyszukiwania po fragmencie adresu e-mail.
 *
 * @param id identyfikator użytkownika (może być null, np. przy tworzeniu nowego obiektu)
 * @param email adres e-mail użytkownika
 */
public record UserEmailDto(
        @Nullable Long id,
        String email
) {
}
