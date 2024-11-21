package com.capgemini.wsb.fitnesstracker.user.internal;
import jakarta.annotation.Nullable;
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName){}