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
     * Retrieves a list of users whose email address contains the specified partial email.
     * The search is case-insensitive.
     *
     * @param email the partial email address to search for
     * @return a list of {@link User} objects whose email address contains the specified partial email,
     *         or an empty list if no users match the search criteria
     */
    List<User> getUserByPartialEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves a list of users whose age is greater than the specified age.
     *
     * This method filters all users in the repository and returns only those whose age,
     * calculated based on their birthdate, is greater than the given age.
     *
     * @param age the minimum age of the users to be retrieved
     * @return a list of {@link User} objects whose age is greater than the specified age,
     *         or an empty list if no users match the search criteria
     */
    List<User> searchUsersByAgeGreaterThan(int age);

}
