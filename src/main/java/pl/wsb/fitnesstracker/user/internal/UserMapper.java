package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * Komponent odpowiedzialny za mapowanie między encją {@link User} a obiektami
 * DTO.
 */
@Component
class UserMapper {

    /**
     * Mapuje encję użytkownika na pełny obiekt DTO.
     *
     * @param user encja użytkownika
     * @return obiekt DTO użytkownika
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Mapuje encję użytkownika na uproszczony obiekt DTO.
     *
     * @param user encja użytkownika
     * @return uproszczony obiekt DTO użytkownika
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Mapuje encję użytkownika na obiekt DTO z adresem email.
     *
     * @param user encja użytkownika
     * @return obiekt DTO z email użytkownika
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(),
                user.getEmail());
    }

    /**
     * Mapuje obiekt DTO użytkownika na encję.
     *
     * @param userDto obiekt DTO użytkownika
     * @return encja użytkownika
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

}