package pl.wsb.fitnesstracker.user.internal;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new User
     *
     * @param user User
     */
    @Override
    public User createUser(final User user) {

        log.info("Creating User " + user);

        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }

        return userRepository.save(user);
    }

    /**
     * Update an existing User
     * @param userUpdateDto userUpdateDto
     * @param user User
     * @return User
     */
    private User updateUser(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.firstName() != null) {
            user.setFirstName(userUpdateDto.firstName());
        }
        if (userUpdateDto.lastName() != null) {
            user.setLastName(userUpdateDto.lastName());
        }
        if (userUpdateDto.email() != null) {
            user.setEmail(userUpdateDto.email());
        }
        if (userUpdateDto.birthDate() != null) {
            user.setBirthdate(userUpdateDto.birthDate());
        }

        return user;
    }

    /**
     * Update an existing User overwrite
     * @param userId Long
     * @param userUpdateDto userUpdateDto
     * @return User
     */
    @Override
    @Transactional
    public Optional<User> updateUser(Long userId, UserUpdateDto userUpdateDto) {
        return userRepository.findById(userId)
                .map(user -> updateUser(userUpdateDto, user))
                .map(userRepository::save);
    }

    /**
     * Delete an existing User
     * @param userId Long
     */
    @Override
    public void deleteUserById(final Long userId) {
        log.info("Deleting User with ID " + userId);

        if (UserServiceImpl.this.getUser(userId).isEmpty()) {
            throw new IllegalArgumentException("There is no user with given ID!");
        }

        userRepository.deleteById(userId);
    }

    /**
     * Get a User by ID
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located User, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Get a User by email
     * @param email String
     * @return An {@link Optional} containing the located User, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Get a User by part of an email
     * @param emailPart String
     * @return List of Users
     */
    @Override
    public List<User> getUsersByEmailPart(String emailPart) {
        return userRepository.findByEmailContainingIgnoreCase(emailPart);
    }

    /**
     * Get all Users born before a given date
     * @param date LocalDate
     * @return List of Users
     */
    @Override
    public List<User> getUsersBornBefore(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }

    /**
     * Get all Users
     * @return List of Users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}