package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;

public record CreateUserDto(String firstName, String lastName, LocalDate birthdate, String email) {}