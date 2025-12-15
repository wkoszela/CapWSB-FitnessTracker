package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserByMailDTO(@Nullable Long id,
                            String email) {
}
