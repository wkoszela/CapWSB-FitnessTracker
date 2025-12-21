package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import java.time.LocalDate;

/**
 * Obiekt DTO (Data Transfer Object) reprezentujący użytkownika.
 *
 * @param id        identyfikator użytkownika
 * @param firstName imię użytkownika
 * @param lastName  nazwisko użytkownika
 * @param birthdate data urodzenia użytkownika
 * @param email     adres email użytkownika
 */
public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {
}