package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;

@Component
class UserMapper {

    public User toEntity(UserDto dto) {

        var user = new User(dto.firstName(), dto.lastName(), 
            dto.birthdate(), dto.email());
        return user;
    }

    public UserDto toDto(User user) {

        var dto = new UserDto(user.getId(), user.getFirstName(), 
            user.getLastName(), user.getBirthdate(), user.getEmail());
        return dto;
    }

    public UserBasicDto toBasicDto(User user) {

        var basicDto = new UserBasicDto(user.getId(), user.getFirstName(),
             user.getLastName());
        return basicDto;
    }

    public UserEmailDto toEmailDto(User user) {

        var emailDto = new UserEmailDto(user.getId(), user.getEmail());
        return emailDto;
    }
}
