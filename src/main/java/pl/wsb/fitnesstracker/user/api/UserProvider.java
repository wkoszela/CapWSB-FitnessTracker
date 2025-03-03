package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.Collection;
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
     * Retrieves all users.
     *
     * @return A {@link List} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves all users with email that contains the given string.
     * The search is case-insensitive.
     *
     * @param emailPart A part of the user's email to be matched
     * @return A {@link List} containing all found users.
     */
    List<User> findUsersByEmailContains(String emailPart);

    /**
     * Retrieves all users with date of birth older than the provided time.
     *
     * @param time The time to use in search
     * @return A {@link List} containing all found users.
     */
    List<User> findUsersOlderThan(LocalDate time);
}
