package pl.wsb.fitnesstracker.statistics.api;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false) // kolumna w tabeli "user"
    private User user;
    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance", nullable = false)
    private double totalDistance;

    @Column(name = "total_calories_burned", nullable = false)
    private int totalCaloriesBurned;

    public Statistics(int totalTrainings, double totalDistance, int totalCaloriesBurned, User user_id) {
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.user = user_id;
    }
}