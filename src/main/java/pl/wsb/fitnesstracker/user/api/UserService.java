package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    List<User> getUserByFullName(String firstName, String lastName);

    List<User> getUserByBirthDate(LocalDate birthDate);

    User findById(Long id);

    void deleteUser(Long id);

    List<User> findUsersOlderThan(int age);

    User updateUser(Long id, User user);

    List<User> searchUsers(Long id, String fullName, String birthDate, String email);
}
