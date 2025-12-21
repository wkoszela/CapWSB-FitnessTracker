package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * klasa zawierajaca id, imie i nazwisko uzytkownika
 * @param id
 * @param firstName
 * @param lastName
 */
public record UserSumDTO(@Nullable Long id, String firstName, String lastName) {
}
