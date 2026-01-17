package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO that exposes only the e‑mail address of a user.
 *
 * <p>Useful for endpoints that only need to verify or update an e‑mail
 * (for example, password reset or account verification).</p>
 *
 * @param id    primary key (nullable for new users)
 * @param email user’s e‑mail address
 */
public record UserEmailDto(@Nullable Long id, String email) {}
