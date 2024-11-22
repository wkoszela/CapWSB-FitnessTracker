package com.capgemini.wsb.fitnesstracker.user.api;

public class UserEmailDto {
    private Long id;
    private String email;

    public UserEmailDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
