/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
package pl.wsb.fitnesstracker.statistics.api;

import pl.wsb.fitnesstracker.user.api.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "statistics")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotalTrainings(int totalTrainings) {
        this.totalTrainings = totalTrainings;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalCaloriesBurned(int totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getTotalTrainings() {
        return totalTrainings;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public int getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance")
    private double totalDistance;

    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    /**
     * Constructor for Statistics entity
     * @param user user entity
     * @param totalTrainings total number of trainings
     * @param totalDistance total distance
     * @param totalCaloriesBurned total calories burned
     */
    public Statistics(User user,
                      int totalTrainings,
                      double totalDistance,
                      int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    /**
     * Constructor for Statistics entity
     * @param id id of the entity
     * @param user user entity
     * @param totalTrainings total number of trainings
     * @param totalDistance total distance
     * @param caloriesBurned total calories burned
     */
    public Statistics(Long id,
                      User user,
                      int totalTrainings,
                      double totalDistance,
                      int caloriesBurned) {
        this.id = id;
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = caloriesBurned;
    }

}