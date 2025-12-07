package pl.wsb.fitnesstracker.user.internal;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoNames;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    @NonNull
    UserDtoNames toDtoNames(User user) {
        return new UserDtoNames(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
}
