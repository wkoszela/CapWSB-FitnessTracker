package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserNameDto(@Nullable Long id, String firstName, String lastName) {

}
