package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics")
@Data
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_trainings")
    private int totalTrainings;

    @Column(name = "total_distance")
    private double totalDistance;

    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;
}