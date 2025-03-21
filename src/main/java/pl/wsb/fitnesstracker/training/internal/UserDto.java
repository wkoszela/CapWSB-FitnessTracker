package pl.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

class UserDto{

    private final Long id;

    private final String firstName;

    private final String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birthdate;

    private final String email;

    public UserDto(Long id, String firstName, String lastName, LocalDate birthdate, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }
}
