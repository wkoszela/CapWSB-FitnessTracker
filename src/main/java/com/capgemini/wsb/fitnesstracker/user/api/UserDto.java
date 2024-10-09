package com.capgemini.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

    public UserDto update(UserDto userDto) {
        return new UserDto(
                userDto.id == null ? id : userDto.id,
                userDto.firstName == null ? firstName : userDto.firstName,
                userDto.lastName == null ? lastName : userDto.lastName,
                userDto.birthdate == null ? birthdate : userDto.birthdate,
                userDto.email == null ? email : userDto.email
        );
    }

    public UserDto addId(Long id) {
        return new UserDto(id, firstName, lastName, birthdate, email);
    }
}
