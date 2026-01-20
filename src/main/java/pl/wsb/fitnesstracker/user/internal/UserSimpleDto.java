package pl.wsb.fitnesstracker.user.internal;

/**
 * Uproszczony Data Transfer Object (DTO) dla encji User.
 * <p>
 * Record klasa zawierająca tylko podstawowe informacje o użytkowniku (ID oraz imię i nazwisko).
 * Używana do zwracania uproszczonej listy użytkowników w odpowiedziach API.
 * Redukuje ilość przesyłanych danych poprzez ograniczenie do niezbędnych pól.
 * </p>
 *
 * @author Fitness Tracker Team
 */
public record UserSimpleDto(Long id, String firstName, String lastName) {
}