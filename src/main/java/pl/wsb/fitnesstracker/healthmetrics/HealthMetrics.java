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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "weight")
    private Double weight;


    @Column(name = "height")
    private Double height;


    @Column(name = "heartRate")
    private Integer heartRate;
}