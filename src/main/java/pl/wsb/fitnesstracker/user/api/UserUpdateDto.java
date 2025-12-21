package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

/**
 * klasa zawierajaca dane uzytkownika do update`u
 * @param firstName
 * @param lastName
 * @param email
 * @param birthdate
 */
public record UserUpdateDto(String firstName,
                            String lastName,
                            String email,
                            LocalDate birthdate) {
}
