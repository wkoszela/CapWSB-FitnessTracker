package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Full data transfer object for {@code User} operations.
 *
 * <p>This DTO contains every field that is needed when creating, updating or
 * retrieving a user. The {@code id} field is optional – it will be omitted
 * in create requests and populated by the persistence layer on retrieval.</p>
 *
 * @param id        primary key (nullable for new users)
 * @param firstName user’s first name
 * @param lastName  user’s last name
 * @param birthdate user’s date of birth; formatted as {@code yyyy-MM-dd}
 * @param email     user’s e‑mail address
 */
public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {}
