package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserSummaryDto(@Nullable Long Id, String firstName, String lastName) {}
