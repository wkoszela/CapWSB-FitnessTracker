package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);
    List<User> findAllUsers();
    Optional<User> findById(Long id);
    Optional<User> getUserByEmail(String email);

    List<User> searchUsers(String firstName, String lastName, LocalDate birthdate);
}
