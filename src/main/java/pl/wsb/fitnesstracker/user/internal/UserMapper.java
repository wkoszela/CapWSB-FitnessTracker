package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.time.Period;

@Component
class UserMapper {

    UserDto toDto(User user) {
        Integer age = null;
        if (user.getBirthdate() != null) {
            age = Period.between(user.getBirthdate(), LocalDate.now()).getYears();
        }

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail(),
                age // <--- Przekazujemy obliczony wiek do DTO
        );
    }

}