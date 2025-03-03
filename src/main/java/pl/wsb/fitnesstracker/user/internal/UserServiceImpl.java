package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
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
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User updateUser(final Long userId, User userInfo) {
        User userToUpdate;
        try {
            userToUpdate = userRepository.getReferenceById(userId);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(userId);
        }
        userToUpdate.setFirstName(userInfo.getFirstName());
        userToUpdate.setLastName(userInfo.getLastName());
        userToUpdate.setBirthdate(userInfo.getBirthdate());
        userToUpdate.setEmail(userInfo.getEmail());
        return userRepository.save(userToUpdate);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByEmailContains(String emailPart) {
        return userRepository.findByEmailContains(emailPart);
    }

    @Override
    public List<User> findUsersOlderThan(LocalDate time) {
        return userRepository.findByBirthdateOlderThan(time);
    }
}