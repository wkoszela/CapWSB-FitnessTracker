package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto( user.getEmail(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getId());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getBirtdate(),
                        userDto.getEmail());
    }

}
