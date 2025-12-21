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

/**
 * Implementacja serwisów {@link UserService} oraz {@link UserProvider} dla
 * użytkowników.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Tworzenie użytkownika {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("Użytkownik posiada już ID, aktualizacja nie jest dozwolona!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        log.info("Usuwanie użytkownika o ID {}", userId);
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("Użytkownik o ID " + userId + " nie został znaleziony");
        }
    }

    @Override
    public User updateUser(final User user) {
        log.info("Aktualizacja użytkownika {}", user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("Użytkownik nie posiada ID, tworzenie nie jest dozwolone!");
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
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByEmailFragment(String emailFragment) {
        log.info("Wyszukiwanie użytkowników z emailem zawierającym: {}", emailFragment);
        return userRepository.findByEmailContainingIgnoreCase(emailFragment);
    }

    @Override
    public List<User> findUsersOlderThan(final LocalDate time) {
        return userRepository.findByBirthdateBefore(time);
    }

}