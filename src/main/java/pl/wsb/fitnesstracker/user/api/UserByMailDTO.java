package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * klasa zawierajaca mail i id uzytkownika
 * @param id
 * @param email
 */
public record UserByMailDTO(@Nullable Long id,
                            String email) {
}
