package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    UserBasicInfoDto getBasicInfoDto(User user) {
        return new UserBasicInfoDto(user.getId(), user.getFirstName() + " " + user.getLastName());
    }
    UserEmailAndID getUserEmailAndID(User user) {
        return new UserEmailAndID(user.getId(), user.getEmail());
    }


}
