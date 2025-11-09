package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance", nullable = false)
    private double totalDistance;

    @Column(name = "total_calories_burned", nullable = false)
    private int totalCaloriesBurned;

    public Statistics(User user_id, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.user_id = user_id;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}