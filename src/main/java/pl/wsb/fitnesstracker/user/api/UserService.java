package pl.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    void deleteUserById(Long userId);

    Optional<User> updateUser(Long userId, UserUpdateDto userUpdateDto);

}
