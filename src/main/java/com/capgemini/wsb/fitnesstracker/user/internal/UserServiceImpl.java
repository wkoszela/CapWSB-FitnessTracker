package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserSummaryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    /**
     * Create user from provided userDto object
     * @param userDto user data to create a new user
     * @return creted user as UserDto object
     */
    @Override
    public UserDto createUser(final UserDto userDto) {
        log.info("Creating User {}", userDto);
        User user = userMapper.toEntity(userDto);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Retrieve user by user's ID primary key
     * @param userId id of the user to be searched
     * @return Opttional object containing user entity
     */
    @Override
    public Optional<User> getUserEntity(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieve user data
     * @param userId id of the user to be searched
     * @return user as UserDto object
     */
    @Override
    public Optional<UserDto> getUser(final Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto);
    }

    /**
     * Retrieve user  data by user's email
     * @param email The email of the user to be searched
     * @return user data as UserEmailDto object
     */
    @Override
    public Optional<UserEmailDto> getUserByEmail(final String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toEmailDto);
    }

    /**
     * Retrieve all users
     * @return list of all users data as UserDto objects
     */
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieve all users
     * @return all users as User objects
     */
    @Override
    public List<User> getAllUsersEntity() {
        return userRepository.findAll()
                .stream()
                .toList();
    }

    /**
     * Retrieve all users basic data like first name and last name
     * @return list of users as UserSummaryDto objects
     */
    @Override
    public List<UserSummaryDto> getAllUsersSimple() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toSummaryDto)
                .toList();
    }

    /**
     * Query all users based on whether Their birthdate was before the date provided
     *
     * @param date date to be compared to
     * @return {@link List<UserDto>} with users born before the {@param date}
     */
    @Override
    public List<UserDto> getUsersOlderThen(final LocalDate date) {
        return userRepository.findAll()
                .stream()
                .filter(s -> isOlder(s, date))
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Delete user object
     * @param userId user primary key
     */
    @Override
    public void deleteUser(final Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
    }

    /**
     * Update user data by given primary key and object
     * @param userId user primary key
     * @param userDto user data to update
     * @return updated user as UserDto object
     */
    @Override
    public UserDto updateUser(final Long userId, UserDto userDto){

        // Since email needs to be unique, query by email and throw error when found duplicate
        if(userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new IllegalArgumentException("User with email " + userDto.email() + " already exists!");
        }
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // Create updated dto and update user based on it
        UserDto oldUserDto = userMapper.toDto(user);
        UserDto newUserDto = oldUserDto.update(userDto).addId(userId);
        User newUser = userMapper.toEntity(newUserDto);
        newUser.setId(userId);

        log.info("Updating User {}", newUserDto);
        userRepository.save(newUser);
        return newUserDto;

    }

    /**
     * Check whether a user's birthdate is before the provided date
     *
     * @param user user from db to be compared
     * @param date provided date
     * @return {@link Boolean} true if {@param user} was born before {@param date}
     */
    private Boolean isOlder(User user, LocalDate date){
        LocalDate userDate = user.getBirthdate();
        return date.isAfter(userDate);
    }
}
