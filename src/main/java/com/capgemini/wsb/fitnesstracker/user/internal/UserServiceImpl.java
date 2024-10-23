package com.capgemini.wsb.fitnesstracker.user.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final TrainingServiceImpl trainingServiceImpl;

    /**
     * @param user
     * @return User
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
     * @param updatedUser
     * @return User
     */
    @Override
    public User updateUser(final User updatedUser) {
        log.info("Updating User {}", updatedUser);
        if (updatedUser.getId() == null) {
            throw new IllegalArgumentException("User not exists in DB, update is not permitted!");
        }

        return userRepository.save(updatedUser);
    }

    /**
     * @param userId
     * @return Optional<User>
     */
    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User {}", userId);
        if (userId == null) {
            throw new IllegalArgumentException("User not exists in DB, update is not permitted!");
        }

        trainingServiceImpl.deleteTrainingByUserId(userId);
        userRepository.deleteById(userId);
    }

    /**
     * @param userId
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * @param email
     * @return Optional<User>
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @return List<User>
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
