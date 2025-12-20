package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Encja reprezentująca użytkownika aplikacji Fitness Tracker.
 * <p>
 * Zawiera podstawowe informacje o użytkowniku takie jak:
 * imię, nazwisko, data urodzenia i adres email.
 * </p>
 * <p>
 * Każdy użytkownik ma przypisane unikalne ID oraz unikalny email.
 * Użytkownik może posiadać wiele szkoleń (Training), metryki zdrowotne (HealthMetrics),
 * statystyki (Statistics) oraz uczestniczyć w wydarzeniach (Event).
 * </p>
 *
 * @see pl.wsb.fitnesstracker.training.api.Training
 * @see pl.wsb.fitnesstracker.healthmetrics.HealthMetrics
 * @see pl.wsb.fitnesstracker.statistics.api.Statistics
 * @see pl.wsb.fitnesstracker.event.UserEvent
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * Unikalny identyfikator użytkownika.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Imię użytkownika.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Nazwisko użytkownika.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Data urodzenia użytkownika.
     * Pole wymagane i nie może być puste.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * Adres email użytkownika.
     * Musi być unikalny w systemie i nie może być pusty.
     * Używany do komunikacji i logowania.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Konstruktor do tworzenia nowego użytkownika.
     *
     * @param firstName imię użytkownika
     * @param lastName  nazwisko użytkownika
     * @param birthdate data urodzenia
     * @param email     adres email (musi być unikalny)
     */
    public User(String firstName, String lastName, LocalDate birthdate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}
