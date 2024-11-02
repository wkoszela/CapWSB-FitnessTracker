package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserEmailSimpleDto(@Nullable Long id, String email){}