package com.capgemini.wsb.fitnesstracker.user.api;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);
    User findUserById(long id);
    User findUserByEmail(String email);
    List<User> findUsersOlderThan(LocalDate time);
    List<User> findUsersByEmailFragment(String emailFragment);
    void deleteUser(long userId);
}
