package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
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
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with given ID not found"));
        this.userRepository.delete(user);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with given email not found")));
        return user;
    }

    @Override
    public Optional<User> getUserById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User with given ID not found");
        }

        return user;
    }

    @Override
    public List<User> getAllUsersOlderThen(LocalDate time) {
        List<User> users = userRepository.findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(ChronoLocalDate.from(time)))
                .toList();

        if (users.isEmpty()) {
            throw new NotFoundException("No users older than given time found");
        }

        return users;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}