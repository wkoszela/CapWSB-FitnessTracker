package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.training.internal.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link User} entities.
 * Provides methods to create, update, delete, and query users.
 * This class also coordinates deleting user-related trainings
 * via the {@link TrainingService}.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final TrainingService trainingService;

    /**
     * Creates a new user in the system.
     *
     * @param user the user to create, must not have an ID set
     * @return the saved user entity with generated ID
     * @throws IllegalArgumentException if the user already has an ID
     */
    @Override
    public User createUser(User user) {
        log.info("Creating user: {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User already has an ID.");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes the user with the given ID.
     * Also deletes all trainings associated with the user.
     *
     * @param id the ID of the user to delete
     * @throws IllegalArgumentException if no user with the given ID exists
     */
    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            trainingService.deleteTrainingsByUserId(id);
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    /**
     * Updates an existing user by ID with new data.
     *
     * @param id the ID of the user to update
     * @param updatedUser the user data to update with
     * @return the updated user entity
     * @throws IllegalArgumentException if no user with the given ID exists
     */
    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updatedUser.getFirstName());
                    existing.setLastName(updatedUser.getLastName());
                    existing.setEmail(updatedUser.getEmail());
                    existing.setBirthdate(updatedUser.getBirthdate());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the user if found, otherwise empty
     */
    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user
     * @return an Optional containing the user if found, otherwise empty
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return list of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Finds users whose email contains the given fragment (case-insensitive).
     *
     * @param fragment the email fragment to search for
     * @return list of users matching the criteria
     */
    @Override
    public List<User> findByPartialEmail(String fragment) {
        return userRepository.findByEmailContainingIgnoreCase(fragment);
    }

    /**
     * Finds users older than the specified age threshold.
     *
     * @param ageThreshold the birthdate threshold to compare against
     * @return list of users older than the specified date
     */
    @Override
    public List<User> findOlderThan(LocalDate ageThreshold) {
        return userRepository.findOlderThan(ageThreshold);
    }
}
