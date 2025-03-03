package pl.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * This method creates a user using data passed in the provided {@link User} object.
     *
     * @param user User to create
     * @return {@link User} containing the created user entity
     */
    User createUser(User user);

    /**
     * This method removes the user by id.
     *
     * @param id Database identifier of the user to remove
     */
    void removeUser(Long id);

    /**
     * This method updates the user with the given id using data passed in the provided {@link User} object.
     *
     * @param id Database identifier of the user to update
     * @param user User info with updates to apply
     * @return {@link User} containing the updated user
     */
    User updateUser(Long id, User user);
}
