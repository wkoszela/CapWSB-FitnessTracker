package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

record UserNameDto(@Nullable Long id, String firstName, String lastName) {

}
