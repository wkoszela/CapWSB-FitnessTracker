package com.capgemini.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

/**
 * UserMapper is a class that is responsible for mapping User objects to UserDto, UserBasicDto objects and vice versa.
 *
 */
@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                                user.getFirstName(),
                                user.getLastName());
    }

    public UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(),
                                user.getEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
