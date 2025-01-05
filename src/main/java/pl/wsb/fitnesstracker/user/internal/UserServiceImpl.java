package pl.wsb.fitnesstracker.user.internal;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);

        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(final Long userId) {
        log.info("Deleting User with ID {}", userId);

        if (user.getId() != null) {
            throw new IllegalArgumentException("There is no user with given ID!");
        }

        userRepository.deleteById(userId);
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
    public List<User> getUsersByEmailPart(String emailPart) {
        return userRepository.findByEmailContainingIgnoreCase(emailPart);
    }

    @Override
    public List<User> getUsersBornBefore(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> updateUser(Long userId, UserUpdateDto userUpdateDto) {
        return userRepository.findById(userId)
                .map(user -> updateUser(userUpdateDto, user))
                .map(userRepository::save);
    }

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

}