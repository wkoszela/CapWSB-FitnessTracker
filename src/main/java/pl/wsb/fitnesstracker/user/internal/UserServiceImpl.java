package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        log.info("Creating new user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(final Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    public User updateUser(final User user) {
        log.info("Updating user: {}", user);
        return userRepository.save(user);
    }

    /**
     * Zwraca użytkowników urodzonych PRZED podaną datą.
     */
    public List<User> findAllUsersOlderThan(LocalDate date) {
        log.info("Searching users born before: {}", date);
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }
}