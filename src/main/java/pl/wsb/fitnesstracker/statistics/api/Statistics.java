package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.lang.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Encja reprezentująca statystyki treningów użytkownika.
 * <p>
 * Zawiera zagregowane informacje o wszystkich treningach danego użytkownika,
 * takie jak: liczba treningów, całkowita dystans i spalane kalorie.
 * </p>
 * <p>
 * Relacja jeden-do-jednego (OneToOne) z encją User - każdy użytkownik ma dokładnie jedne statystyki.
 * Ładowanie danych jest leniwe (LAZY) dla optymalizacji wydajności.
 * </p>
 *
 * @see User
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    /**
     * Unikalny identyfikator statystyk.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * Użytkownik, którego statystyki są przechowywane.
     * Relacja jeden-do-jednego (OneToOne) - obowiązkowa.
     * Zaladowanie danych jest leniwe (LAZY).
     */
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    /**
     * Całkowita liczba treningów wykonanych przez użytkownika.
     * Pole wymagane.
     */
    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    /**
     * Całkowita dystans przebyta podczas wszystkich treningów (w kilometrach).
     * Pole opcjonalne, wartość domyślna to 0.0.
     */
    @Column(name = "total_distance")
    private double totalDistance;

    /**
     * Całkowita liczba spalonych kalorii podczas wszystkich treningów.
     * Pole opcjonalne, wartość domyślna to 0.
     */
    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    public Statistics(@Nullable Long id, User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.id = id;
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}