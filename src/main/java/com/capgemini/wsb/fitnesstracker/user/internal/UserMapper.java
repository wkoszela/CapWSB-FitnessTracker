package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    /**
     * Konwertuje obiekt User na UserDto.
     *
     * @param user obiekt User do konwersji
     * @return UserDto zawierający podstawowe informacje o użytkowniku
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }


    /**
     * Konwertuje obiekt UserDto na User.
     *
     * @param userDto obiekt UserDto do konwersji
     * @return User utworzony na podstawie danych z UserDto
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * Konwertuje obiekt User na UserSimpleDto.
     *
     * @param user obiekt User do konwersji
     * @return UserSimpleDto zawierający uproszczone dane użytkownika (ID, imię, nazwisko)
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }


}