package pl.wsb.fitnesstracker.user.internal;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoNames;

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
}
