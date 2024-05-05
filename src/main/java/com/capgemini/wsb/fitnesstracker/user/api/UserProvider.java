package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Finds and returns a list of users with the specified email.
     *
     * @param  email  the email address of the users to search for
     * @return        a list of users with the specified email
     */
    List<User> findUsersByEmail(String email);

    /**
     * Finds and returns a list of users with the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return        a list of users with the specified minimum age
     */
    List<User> findUsersByMinAge(int minAge);

}
