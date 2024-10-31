package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * A record representing user ID and email information.
 *
 * @param id the unique identifier of the user
 * @param email the email address of the user
 */
public record UserIdEmailInfo(Long id, String email) {}