package com.capgemini.wsb.fitnesstracker.user.api;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String email){
        super("User with email is already exists".formatted(email));
    }
}
