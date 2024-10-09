package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider{

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
    public List<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserBasicInfoDto> findAllBasicInfo() {
        return userRepository.findAll().stream()
                .map(user -> new UserBasicInfoDto(user.getId(), user.getFirstName() + " " + user.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUserById(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUserById(final Long userId) {
        userRepository.deleteUserById(userId);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("{User ID not found: " + id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setBirthdate(userDetails.getBirthdate());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }
}