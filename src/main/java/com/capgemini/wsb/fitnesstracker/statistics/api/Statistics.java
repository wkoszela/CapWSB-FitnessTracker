package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

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

}