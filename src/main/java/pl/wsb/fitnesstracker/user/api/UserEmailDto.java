package pl.wsb.fitnesstracker.user.api;

/**
 * DTO representing a limited view of user data containing only the ID and email address.
 *
 * @param id    the user ID
 * @param email the user email address
 */
public record UserEmailDto(Long id, String email) {
}
