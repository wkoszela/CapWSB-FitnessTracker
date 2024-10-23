package com.capgemini.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;

import com.capgemini.wsb.fitnesstracker.user.api.User;

@Component
class UserMapper {

    /**
     * @param user
     * @return UserDto
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * @param user
     * @return UserSimpleDto
     */
    UserSimpleDto toSimple(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * @param user
     * @return UserEmailDto
     */
    UserEmailDto toEmail(User user) {
        return new UserEmailDto(user.getId(),
                user.getEmail());
    }

    /**
     * @param user
     * @return UserOlderDto
     */
    UserOlderDto toOlder(User user) {
        return new UserOlderDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate());
    }

    /**
     * @param userDto
     * @return User
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * @param user
     * @param userDto
     * @return User
     */
    User updateEntity(User user, UserDto userDto) {
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthdate(userDto.birthdate());
        user.setEmail(userDto.email());
        return user;
    }
}
