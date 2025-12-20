package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Returns a list of all users.
     *
     * @return A list of all users
     */
    List<User> findAllUsers();

    /**
     * Returns a list of all users older than the specified date.
     *
     * @param time The date to compare with
     * @return A list of users older than the specified date
     */
    List<User> findAllUsersOlderThan(LocalDate time);

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Updates a user.
     *
     * @param id The ID of the user to update
     * @param user The user data to update
     * @return The updated user
     */
    User updateUser(Long id, User user);

}
