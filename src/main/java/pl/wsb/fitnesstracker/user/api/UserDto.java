package pl.wsb.fitnesstracker.user.api;

import org.springframework.lang.Nullable;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) dla encji User.
 * <p>
 * Record klasa zawierająca wszystkie informacje o użytkowniku dla transferu danych HTTP.
 * Używana do komunikacji pomiędzy controllerem a klientem (API).
 * Zapewnia hermetyzację danych encji i umożliwia elastyczną serializację JSON.
 * </p>
 * <p>
 * Pole {@code id} jest opcjonalne (marked as @Nullable) dla nowych użytkowników
 * (przed zapisem do BD), gdzie ID jest generowany automatycznie przez bazę danych.
 * </p>
 *
 * @see User
 *
 * @author Fitness Tracker Team
 */
public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
}