package pl.wsb.fitnesstracker.user.api;

/**
 * Record used for returning minimal user information during email search.
 */
public record UserEmailDto(Long id, String email) {
}