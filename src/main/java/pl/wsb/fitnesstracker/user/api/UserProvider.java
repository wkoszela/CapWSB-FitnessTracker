// This code snippet defines an interface named `UserProvider` in the package
// `pl.wsb.fitnesstracker.user.api`. The interface declares several methods for retrieving and
// searching user information. Here is a summary of what each method does:
package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be
     * returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or
     *         {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will
     * be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or
     *         {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return List of all users
     */
    List<User> findAllUsers();

    /**
     * Finds users whose email contains the given fragment (case-insensitive).
     *
     * @param emailFragment The fragment of the email to search for
     * @return List of matching users
     */
    List<User> findUsersByEmailFragment(String emailFragment);

    /**
     * Finds users older than the specified age.
     *
     * @param time The date threshold
     * @return List of users older than 'time'
     */
    List<User> findUsersOlderThan(java.time.LocalDate time);

}