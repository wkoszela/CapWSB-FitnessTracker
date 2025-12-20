package pl.wsb.fitnesstracker.user.api;

import org.springframework.lang.Nullable;
import java.time.LocalDate;

public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
}