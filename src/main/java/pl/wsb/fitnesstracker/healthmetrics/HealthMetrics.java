/**
 * The `HealthMetrics` class represents health metrics data for a user including weight, height, heart
 * rate, and date, with an updated constructor that includes the height parameter.
 */
/**
 * The `HealthMetrics` class represents health metrics data for a user including weight, height, heart
 * rate, and date.
 */
package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

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

    // --- DODANE POLE ---
    @Column(nullable = false)
    private double height;
    // -------------------

    @Column(nullable = false)
    private int heartRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Zaktualizowany konstruktor (dodany parametr height)
    public HealthMetrics(User user, double weight, double height, int heartRate, LocalDate date) {
        this.user = user;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
        this.date = date;
    }
}