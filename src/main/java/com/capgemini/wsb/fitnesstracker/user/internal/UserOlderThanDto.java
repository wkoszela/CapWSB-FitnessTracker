package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;


public record UserOlderThanDto(String firstName, String lastName, LocalDate birthdate) {}