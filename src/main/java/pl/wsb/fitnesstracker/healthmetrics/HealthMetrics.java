package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

/**
 * Encja reprezentująca metryki zdrowotne użytkownika (waga, wzrost, tętno).
 */
@Entity
@Table(name = "health_metrics")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private int heartRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Konstruktor tworzący nową metrykę zdrowotną.
     *
     * @param user      użytkownik, do którego należą metryki
     * @param weight    waga użytkownika
     * @param height    wzrost użytkownika
     * @param heartRate tętno użytkownika
     * @param date      data pomiaru
     */
    public HealthMetrics(User user, double weight, double height, int heartRate, LocalDate date) {
        this.user = user;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
        this.date = date;
    }
}