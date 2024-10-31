package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * A record representing user ID and birthdate information.
 *
 * @param id the unique identifier of the user
 * @param birthday the birthdate of the user in the format "yyyy-MM-dd"
 */
public record UserIdBirthdayInfo(Long id, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {}

