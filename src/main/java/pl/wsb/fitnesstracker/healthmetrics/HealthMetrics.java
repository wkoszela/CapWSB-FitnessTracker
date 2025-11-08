package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

public class HealthMetrics {

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

        @ManyToOne
        @JoinColumn (name = "user_id", nullable = false)
        private User user;

        @Column(name = "date", nullable = false)
        private LocalDate date;

        @Column(name = "weight")
        private Double weight;

        @Column(name = "height")
        private Integer height;

        @Column(name = "heartRate")
        private Integer HeartRate;

        public HealthMetrics(User user, LocalDate date, Double weight, Integer height, Integer heartRate) {
            this.user = user;
            this.date = date;
            this.weight = weight;
            this.height = height;
            this.heartRate = heartRate;
        }

}
