package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_session")
@Data
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "start_latitude")
    private double startLatitude;

    @Column(name = "start_longitude")
    private double startLongitude;

    @Column(name = "end_latitude")
    private double endLatitude;

    @Column(name = "end_longitude")
    private double endLongitude;

    @Column(name = "altitude")
    private double altitude;
}