package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

/**
 * UserIdEmailDto is a representation of User Id and Email entity.
 */
public record UserIdEmailDto(Long id, String email) {
}
