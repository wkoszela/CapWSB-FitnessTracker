package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
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
    public List<User> getUserByFullName(final String firstName, final String lastName) {
        return userRepository.findUsersByFullName(firstName, lastName);
    }

    @Override
    public List<User> getUserByBirthDate(final LocalDate birthDate) {
        return userRepository.findByBirthDate(birthDate);
    }

    @Override
    public User findById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findUsersOlderThan(final int age) {
        return userRepository.findOlderThan(age);
    }

    @Override
    public User updateUser(final Long id, final User user) {
        log.info("Updating User {}", user);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUsers(Long id, String fullName, String birthDate, String email) {
        return userRepository.search(id, fullName, birthDate, email);
    }
}