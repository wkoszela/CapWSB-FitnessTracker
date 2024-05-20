package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user) throws UserAlreadyExistException ;
    public Optional<User> getUser(final Long userId);
    public Optional<User> getUserByEmail(final String email);
    public List<User> findAllUser();
    public User userUpdate(Long id, User newUserData);
    public List<User> findByMailCase(String emailCase);
    public List<User> findUsersOlderThan(int age);
    public boolean delete(Long userId);




}
