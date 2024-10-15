package com.capgemini.wsb.fitnesstracker.user.api;

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
    Optional<User> getUserEntity(Long userId);

    /**
     * Retrieves all users.
     *
     * @return A {@link List<User>} containing the all users
     */
    List<User> getAllUsersEntity();

    /**
     * Retrieves a userDto based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located userDto, or {@link Optional#empty()} if not found
     */
    Optional<UserDto> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<UserEmailDto> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return A {@link List<UserDto>} containing the all users
     */
    List<UserDto> getAllUsers();

    /**
     * Retrieves all users in simple format.
     *
     * @return A {@link List<UserSummaryDto>} containing the all users in simple format
     */
    List<UserSummaryDto> getAllUsersSimple();

    /**
     * Retrieves all users older than specified date.
     *
     * @param date date used to compare with user's birthdate
     * @return A {@link List<UserDto>} containing the all users born before specified date
     */
    List<UserDto> getUsersOlderThen(LocalDate date);
}
