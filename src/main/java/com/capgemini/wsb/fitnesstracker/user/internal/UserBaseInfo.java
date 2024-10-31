package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * A record representing basic user information.
 *
 * @param id the unique identifier of the user
 * @param firstName the first name of the user
 * @param lastName the last name of the user
 */
public record UserBaseInfo(Long id, String firstName, String lastName) {}