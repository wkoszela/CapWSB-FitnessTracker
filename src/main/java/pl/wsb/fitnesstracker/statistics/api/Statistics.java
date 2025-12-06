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
    @Nullable
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance")
    private double totalDistance;

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