package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the
 * API.
 * Implementing classes are responsible for executing changes within a database
 * transaction, whether by continuing an existing transaction or creating a new
 * one if required.
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
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to delete
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user.
     *
     * @param user The user entity with updated data
     * @return The updated user
     */
    User updateUser(User user);

}