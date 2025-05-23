package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record SimpleUserDto(@Nullable Long id, String firstName, String lastName) {

}