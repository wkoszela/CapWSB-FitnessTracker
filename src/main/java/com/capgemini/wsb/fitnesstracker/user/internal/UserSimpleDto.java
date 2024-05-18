package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * UserBasicDto is a DTO class that represents basic information about a user.
 * It is used to transfer data between the controller and the service.
 *
 */
record UserSimpleDto(long id, String firstName, String lastName){}

