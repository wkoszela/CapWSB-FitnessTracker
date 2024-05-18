package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * UserBasicDto is a DTO class that represents basic information about a user (ID & email).
 * It is used to transfer data between the controller and the service.
 *
 */
public record UserEmailDto(long id, String email){}

