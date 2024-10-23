package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;

public record UserOlderDto(@Nullable Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) {
}
