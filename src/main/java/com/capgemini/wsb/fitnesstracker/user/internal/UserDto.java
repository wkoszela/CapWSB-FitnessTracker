package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.transaction.UserTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor



public class UserDto{
    private String email;

    private String lastName;

    private String firstName;

    private LocalDate birtdate;

    private Long id;

    public UserDto(
            final String email,
            final String lastName,
            final String firstName,
            final LocalDate birtdate)

    {
        this.email=email;
        this.birtdate=birtdate;
        this.lastName=lastName;
        this.firstName=firstName;
    }
}
