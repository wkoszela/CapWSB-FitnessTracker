package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper do konwersji między encją User a DTOs.
 * <p>
 * Odpowiada za transformację danych między warstwą HTTP a warstwą biznesową.
 * Umożliwia konwersję w obie strony oraz między różnymi DTOs (pełny i uproszczone).
 * </p>
 * <p>
 * Komponenty mapujące:
 * - User <-> UserDto (pełny DTO z wszystkimi danymi)
 * - User -> UserSimpleDto (uproszczony DTO - tylko imię, nazwisko, ID)
 * - User -> UserEmailSimpleDto (DTO z email - do identyfikacji)
 * - Aktualizacja User z UserDto (updateUserFromDto)
 * </p>
 *
 * @author Fitness Tracker Team
 */
@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
    }

    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public UserEmailSimpleDto toEmailSimpleDto(User user) {
        return new UserEmailSimpleDto(
                user.getId(),
                user.getEmail()
        );
    }

    public void updateUserFromDto(User user, UserDto userDto) {
        if (userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if (userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }
        if (userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }
    }
}
