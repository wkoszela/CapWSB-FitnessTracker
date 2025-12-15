package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record UserCreateDTO(String firstName,
                            String lastName,
                            String email,
                            @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) {
}
