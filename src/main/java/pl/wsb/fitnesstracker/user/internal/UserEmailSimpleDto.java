package pl.wsb.fitnesstracker.user.internal;

/**
 * Uproszczony Data Transfer Object (DTO) dla encji User zawierający email.
 * <p>
 * Record klasa zawierająca tylko ID i adres email użytkownika.
 * Używana do zwracania danych identyfikujących użytkownika poprzez email.
 * Przydatna w operacjach wymagających unikalnej identyfikacji użytkownika bez pełnych danych.
 * </p>
 *
 * @author Fitness Tracker Team
 */
public record UserEmailSimpleDto(Long id, String email) {
}