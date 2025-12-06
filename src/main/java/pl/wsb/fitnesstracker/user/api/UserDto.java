package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import java.time.LocalDate;

// Usuwamy pole "Integer age", wracamy do standardu
public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
}