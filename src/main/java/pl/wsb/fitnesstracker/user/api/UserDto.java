package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;

public record UserDto(@Nullable Long id, String firstName, String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {

}
