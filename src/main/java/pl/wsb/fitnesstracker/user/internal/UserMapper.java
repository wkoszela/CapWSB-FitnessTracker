package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserByMailDTO;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSumDTO;

@Component
class UserMapper {
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }
    UserByMailDTO toMailDTO(User user) {
        return new UserByMailDTO(user.getId(),
                user.getEmail());
    }

    UserSumDTO toSunDTO(User user) {
        return new UserSumDTO(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    User fromCreate(UserDto dto) {
        return new User(dto.firstName(), dto.lastName(), dto.birthdate(), dto.email());
    }

}
