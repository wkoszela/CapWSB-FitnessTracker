package pl.wsb.fitnesstracker.user.api;

/**
 * DTO representing a simplified view of user data containing ID and name.
 *
 * @param id        the user ID
 * @param firstName the user first name
 * @param lastName  the user last name
 */
public record UserSimpleDto(Long id, String firstName, String lastName) {
}
