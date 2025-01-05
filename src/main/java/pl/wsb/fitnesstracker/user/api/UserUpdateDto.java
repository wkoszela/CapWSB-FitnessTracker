package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

/**
 * UserUpdateDto is a representation of UserUpdate entity.
 */
public record UserUpdateDto(String firstName, String lastName, String email, LocalDate birthDate) {
}
