package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Serwis implementujący logikę zarządzania użytkownikami.
 * Obejmuje operacje CRUD oraz dodatkowe wyszukiwania.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        log.info("Creating user: {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User already has an ID.");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

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

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findByPartialEmail(String fragment) {
        return userRepository.findByEmailContainingIgnoreCase(fragment);
    }

    public List<User> findOlderThan(int age) {
        return userRepository.findOlderThan(age);
    }
}
