package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param  user  the user object containing the details of the user to be created
     * @return       the created user object
     */
    User createUser(User user);

    /**
     * Deletes a user.
     *
     * @param  user  the user object to be deleted
     */
    void deleteUser(User user);

    /**
     * Updates an existing user with the provided user object.
     *
     * @param  user  the user object containing the updated details
     * @return       the updated user object
     */
    User updateUser(User user);

}
