package pl.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * UserEmailSimpleDto is a simple representation of User entity.
 */
public record UserEmailSimpleDto(@Nullable Long id, String email){}