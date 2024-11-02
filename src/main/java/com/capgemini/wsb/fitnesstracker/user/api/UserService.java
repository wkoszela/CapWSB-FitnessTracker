package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user
     *
     * @param user to be created
     *
     * @return A created {@link User}
     */
    User createUser(User user);

    /**
     * Deletes user that matches the specified id
     *
     * @param id of the user to be deleted
     */
    void deleteUser(Long id);
}
