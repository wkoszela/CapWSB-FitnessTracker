package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Obiekt DTO (Data Transfer Object) reprezentujący podstawowe dane
 * identyfikacyjne użytkownika (ID i email).
 *
 * @param id    identyfikator użytkownika
 * @param email adres email użytkownika
 */
public record UserEmailDto(@Nullable Long id, String email) {
}