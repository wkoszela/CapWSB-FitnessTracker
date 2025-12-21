package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Obiekt DTO (Data Transfer Object) reprezentujący uproszczone dane
 * użytkownika.
 *
 * @param id        identyfikator użytkownika
 * @param firstName imię użytkownika
 * @param lastName  nazwisko użytkownika
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName, String email) {
}