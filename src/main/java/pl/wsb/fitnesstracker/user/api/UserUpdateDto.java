package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

public record UserUpdateDto(String firstName,
                            String lastName,
                            String email,
                            LocalDate birthdate) {
}
