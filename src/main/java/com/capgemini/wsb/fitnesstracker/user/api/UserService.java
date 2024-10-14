package com.capgemini.wsb.fitnesstracker.user.api;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);
    List<UserSummaryDto> getAllUsers();
    UserSummaryDto createUser(CreateUserDto createUserDto);
    List<UserSummaryDto> getUsersOlderThan(int age);
    UserSummaryDto updateUser(Long id, UpdateUserDto updateUserDto);
}
