package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance")
    private double totalDistance;

    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    public Statistics(User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}