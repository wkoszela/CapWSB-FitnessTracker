package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Getter
@Entity
@Table(name = "trainings")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "training_id")
    private Training training;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @Column(name = "startLatitude", nullable = false)
    private double startLatitude;

    @Column(name = "startLongitude", nullable = false)
    private double startLongitude;

    @Column(name = "endLatitude", nullable = false)
    private double endLatitude;

    @Column(name = "endLongitude", nullable = false)
    private double endLongitude;

    @Column(name = "altitude", nullable = false)
    private double altitude;

    public WorkoutSession(
            final int trainingId,
            final String timestamp,
            final double startLatitude,
            final double startLongitude,
            final double endLatitude,
            final double endLongitude,
            final double altitude) {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }

}
