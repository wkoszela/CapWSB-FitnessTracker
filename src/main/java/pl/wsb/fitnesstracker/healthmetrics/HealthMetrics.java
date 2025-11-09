package pl.wsb.fitnesstracker.healthmetrics;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "HealthMetrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "heartRate")
    private int heartRate;

    public HealthMetrics(User user_id, LocalDate date, double weight, double height, int heartRate) {
        this.user_id = user_id;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }
}
