package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param  user  the user object containing the details of the user to be created
     * @return       the created user object
     * @throws IllegalArgumentException if the user already has a DB ID and update is not permitted
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes a user.
     *
     * @param  user  the user object to be deleted
     */
    @Override
    public void deleteUser(final User user) {
        userRepository.delete(user);
    }

    /**
     * Retrieves a user based on their ID.
     *
     * @param  userId  the ID of the user to be searched
     * @return         an {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user based on their email.
     *
     * @param  email  the email of the user to be searched
     * @return        an {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all users from the user repository.
     *
     * @return a list of User objects representing all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Finds and returns a list of users with the specified email.
     *
     * @param  email  the email address of the users to search for
     * @return        a list of users with the specified email
     */
    @Override
    public List<User> findUsersByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    /**
     * Finds and returns a list of users with an age greater than or equal to the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return         a list of users with an age greater than or equal to the specified minimum age
     */
    @Override
    public List<User> findUsersByMinAge(int minAge) {
        return userRepository.findAllByMinAge(minAge);
    }

    /**
     * Updates a user in the database.
     *
     * @param  user  the user object to be updated
     * @return       the updated user object
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

}