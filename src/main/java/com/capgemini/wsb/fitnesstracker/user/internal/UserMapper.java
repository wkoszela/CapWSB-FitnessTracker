package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to convert
     * @return a UserDto containing user information
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto the UserDto to convert
     * @return a User entity created from the UserDto data
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * Converts a User entity to a UserBaseInfo, containing basic user information.
     *
     * @param user the User entity to convert
     * @return a UserBaseInfo object with basic information
     */
    UserBaseInfo toUserBaseInfo(User user) {
        return new UserBaseInfo(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts a User entity to a UserIdEmailInfo, containing user ID and email.
     *
     * @param user the User entity to convert
     * @return a UserIdEmailInfo object with user ID and email
     */
    UserIdEmailInfo toUserIdEmail(User user) {
        return new UserIdEmailInfo(
                user.getId(),
                user.getEmail());
    }

    /**
     * Converts a User entity to a UserIdBirthdayInfo, containing user ID and birthdate.
     *
     * @param user the User entity to convert
     * @return a UserIdBirthdayInfo object with user ID and birthdate
     */
    UserIdBirthdayInfo toUserIdBirthdayDto(User user) {
        return new UserIdBirthdayInfo(user.getId(), user.getBirthdate());
    }
}
