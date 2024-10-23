package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

public record UserEmailDto(@Nullable Long id, String email) {
}