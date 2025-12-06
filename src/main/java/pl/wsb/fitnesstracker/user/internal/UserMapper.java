package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.BasicDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserAddDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    BasicDto toBasicDto(User user) {
        return new BasicDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()

        );

    }

    User toEntity(UserAddDto userAddDto) {
        return new User(
                userAddDto.firstName(),
                userAddDto.lastName(),
                userAddDto.birthdate(),
                userAddDto.email()
        );
    }


}
