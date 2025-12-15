package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record UserDetailDTO(@Nullable Long id, String firstName, String lastName,
                            String email, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) {
}
