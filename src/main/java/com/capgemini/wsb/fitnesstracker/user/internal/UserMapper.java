package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserDto toDto(User user) {
        return new UserDto( user.getEmail(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getId());
    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getBirtdate(),
                        userDto.getEmail());
    }

}
