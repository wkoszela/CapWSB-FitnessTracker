package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;
import lombok.Getter;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

/**
 * Encja reprezentująca metryki zdrowotne użytkownika.
 * <p>
 * Przechowuje informacje o zdrowiu użytkownika na konkretny dzień, takie jak:
 * waga, wzrost, tętno spoczynkowe itp.
 * </p>
 * <p>
 * Każda metryka zdrowotna jest przypisana do konkretnego użytkownika (User)
 * i konkretnego dnia (date).
 * </p>
 * <p>
 * Umożliwia śledzenie zmian zdrowotnych użytkownika w czasie.
 * </p>
 *
 * @see User
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "Health_Metrics")
@Getter
public class HealthMetrics {

    /**
     * Unikalny identyfikator metryki zdrowotnej.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Użytkownik, do którego przypisana jest metryka zdrowotna.
     * Relacja wiele-do-jednego (ManyToOne) - obowiązkowa.
     * Zaladowanie danych jest leniwe (LAZY).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Data, na którą zarejestrowana jest metryka zdrowotna.
     * Pole wymagane.
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /**
     * Waga użytkownika w kilogramach.
     * Pole opcjonalne.
     */
    @Column
    private Double weight;

    /**
     * Wzrost użytkownika w centymetrach.
     * Pole opcjonalne.
     */
    @Column
    private Double height;

    /**
     * Tętno spoczynkowe użytkownika (bity na minutę).
     * Pole opcjonalne.
     */
    @Column(name = "heart_rate")
    private Integer heartRate;

    /**
     * Konstruktor bez argumentów.
     */
    public HealthMetrics() {
    }

    /**
     * Konstruktor do tworzenia nowej metryki zdrowotnej.
     *
     * @param user      użytkownik
     * @param date      data pomiaru
     * @param weight    waga użytkownika
     * @param height    wzrost użytkownika
     * @param heartRate tętno spoczynkowe
     */
    public HealthMetrics(User user, LocalDate date, Double weight, Double height, Integer heartRate) {
        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }


}
