package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.transaction.UserTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class UserDto{
    private String email;
    private String lastName;
    private String firstName;
    private LocalDate birtdate;
    public UserDto(
            final String email,
            final String lastName,
            final String firstName,
            final LocalDate birtdate){
        this.email=email;
        this.birtdate=birtdate;
        this.lastName=lastName;
        this.firstName=lastName;
    }
}
