package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Basic representation of a user returned by listing endpoints.
 */
public record UserBasicDto(@Nullable Long id, String fullName) {

}
