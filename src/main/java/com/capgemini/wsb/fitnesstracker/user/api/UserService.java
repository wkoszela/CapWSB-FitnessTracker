package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {
    /**
     * Creates a user.
     * If the user with the same ID exists, then {@link IllegalArgumentException} will be thrown.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user) throws IllegalArgumentException;

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Updates a user.
     * If the user with the specified ID doesn't exist, then {@link UserNotFoundException} will be thrown.
     *
     * @param id The ID of the user to be updated
     * @param updatedUser The user to be created
     * @return The updated user
     */
    User updateUser(Long id, User updatedUser) throws UserNotFoundException;
}
