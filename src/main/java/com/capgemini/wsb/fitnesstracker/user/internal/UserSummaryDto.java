package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

record UserSummaryDto(@Nullable Long Id, String firstName, String lastName) {}
