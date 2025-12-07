package pl.wsb.fitnesstracker.user.api;

/**
 * Record used for returning simplified user information (id and name only).
 */
public record UserSimpleDto(Long id, String firstName, String lastName) {
}