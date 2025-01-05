package pl.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * UserSimpleDto is a representation of User Simple entity.
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) { }
