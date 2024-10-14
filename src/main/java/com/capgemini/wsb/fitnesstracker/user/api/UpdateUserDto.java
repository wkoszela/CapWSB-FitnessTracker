package com.capgemini.wsb.fitnesstracker.user.api;


import java.time.LocalDate;

public record UpdateUserDto(String firstName, String lastName, String email, String username, LocalDate birthdate) {}
