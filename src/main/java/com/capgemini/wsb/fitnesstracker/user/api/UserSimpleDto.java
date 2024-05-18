package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * UserBasicDto is a DTO class that represents basic information about a user.
 * It is used to transfer data between the controller and the service.
 *
 */
public record UserSimpleDto(long id, String firstName, String lastName){}

