package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Lightweight DTO used for summary or list views.
 *
 * <p>Only the user’s identifier and names are exposed, which keeps payloads
 * small when many users must be transmitted (e.g., in a follower list).</p>
 *
 * @param id        primary key (nullable for new users)
 * @param firstName user’s first name
 * @param lastName  user’s last name
 */
public record UserBasicDto(@Nullable Long id, String firstName, String lastName) {}
