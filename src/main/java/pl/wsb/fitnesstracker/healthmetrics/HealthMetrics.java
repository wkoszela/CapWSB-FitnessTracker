package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "heartRate", nullable = false)
    private Double heartRate;

    public HealthMetrics(LocalDate date,
                         Double weight,
                         Double height,
                         Double heartRate) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }
}