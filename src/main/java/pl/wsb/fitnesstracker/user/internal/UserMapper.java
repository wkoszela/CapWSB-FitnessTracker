package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

/**
 * Komponent mapujący encję {@link User} na {@link UserDto} i odwrotnie.
 */
@Component
public class UserMapper {
    /**
     * Konwertuje encję {@link User} na pełny obiekt transferu danych {@link UserDto}.
     *
     * @param user encja użytkownika
     * @return obiekt DTO z pełnymi danymi użytkownika
     */
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }
    /**
     * Tworzy encję {@link User} na podstawie obiektu transferu danych {@link UserDto}.
     * Przy tworzeniu nowego użytkownika, ID może być null.
     *
     * @param dto obiekt DTO z danymi użytkownika
     * @return encja użytkownika
     */
    public User toEntity(UserDto dto) {
        return new User(
                dto.firstName(),
                dto.lastName(),
                dto.birthdate(),
                dto.email()
        );
    }

    /**
     * Konwertuje encję {@link User} na uproszczony obiekt transferu danych {@link SimpleUserDto}
     * zawierający tylko imię i nazwisko.
     *
     * @param user encja użytkownika
     * @return uproszczony obiekt DTO z imieniem i nazwiskiem użytkownika
     */
    public SimpleUserDto toSimpleDto(User user) {
        return new SimpleUserDto(
                user.getFirstName(),
                user.getLastName()
        );
    }

    /**
     * Konwertuje encję {@link User} na obiekt transferu danych {@link UserEmailDto}
     * zawierający ID i adres e-mail użytkownika.
     *
     * @param user encja użytkownika
     * @return obiekt DTO z ID i e-mailem użytkownika
     */
    public UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(
                user.getId(),
                user.getEmail()
        );
    }
}
