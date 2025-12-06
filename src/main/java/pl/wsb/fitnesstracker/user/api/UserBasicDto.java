package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserBasicDto(@Nullable Long id, String firstName, String lastName) {

}
