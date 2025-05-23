package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying and querying {@link User} entities.
 * Implementing classes are responsible for database operations such as creating,
 * updating, deleting, and searching users.
 *
 * This interface defines application-level services exposed to other modules.
 */
public interface UserService {

    /**
     * Creates a new user in the system.
     * The user must not already have an ID (i.e., it should be a new user).
     *
     * @param user the user to create
     * @return the saved user with a generated ID
     * @throws IllegalArgumentException if the user already has an ID
     */
    User createUser(User user);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Updates an existing user's information.
     * If the user does not exist, an exception is thrown.
     *
     * @param id the ID of the user to update
     * @param updatedUser the new user data to apply
     * @return the updated user
     * @throws IllegalArgumentException if the user with the given ID is not found
     */
    User updateUser(Long id, User updatedUser);

    /**
     * Finds users whose email contains the given fragment (case-insensitive).
     *
     * @param fragment the email fragment to search for
     * @return list of users whose email contains the given fragment
     */
    List<User> findByPartialEmail(String fragment);

    /**
     * Finds users who are older than the given age threshold (i.e., born before the specified date).
     *
     * @param ageThreshold the maximum birthdate (e.g., LocalDate.now().minusYears(18))
     * @return list of users older than the specified age
     */
    List<User> findOlderThan(LocalDate ageThreshold);
}
