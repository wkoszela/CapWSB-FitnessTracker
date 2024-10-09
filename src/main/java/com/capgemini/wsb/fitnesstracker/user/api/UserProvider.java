package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UserBasicInfoDto;

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
    List<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieve users first name, last name and id
     * @return An {@link List} containing the all users,
     */
    List<UserBasicInfoDto> findAllBasicInfo();

    /**
     * Retrieve user by his ID
     * @param userId User ID
     * @return An {@link Optional} containing the user
     */
    Optional<User> findUserById(Long userId);

    /**
     * Delete user by his ID
     * @param userId User ID
     */
    void deleteUserById(Long userId);

}