package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserSummaryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }


    @Override
    public Optional<UserDto> getUser(final Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserEmailDto> getUserByEmail(final String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toEmailDto);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<UserSummaryDto> findAllUsersSimple() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toSummaryDto)
                .toList();
    }

    @Override
    public List<UserDto> findUsersOlderThen(LocalDate age) {
        return userRepository.findAll()
                .stream()
                .filter(s -> isOlder(s, age))
                .map(userMapper::toDto)
                .toList();
    }


    @Override
    public void deleteUser(final Long userId) {
        var user = userRepository.findById(userId);
        if(user.isPresent()) {
            userRepository.deleteById(userId);
        }
        throw new NotFoundException("User not found");
    }

    private Boolean isOlder(User user, LocalDate age){
        LocalDate userDate = user.getBirthdate();
        return age.isAfter(userDate);
    }
}
