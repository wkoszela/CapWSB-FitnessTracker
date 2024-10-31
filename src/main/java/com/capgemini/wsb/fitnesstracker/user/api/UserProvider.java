package com.capgemini.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
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
     * Retrieves a user by their first and last name.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @return an Optional containing the User if found, or an empty Optional if not found
     */
    Optional<User> getUserByNameSurname(String firstName, String lastName);

    /**
     * Retrieves a list of users with a specific birthdate.
     *
     * @param birthdate the birthdate of the users in the format "yyyy-MM-dd"
     * @return a list of users who have the specified birthdate
     */
    List<User> getUserByBirthdate(@JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate);

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUserById(Long id);
}
