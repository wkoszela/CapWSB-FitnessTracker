package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "WorkoutSession")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "trainingId", nullable = false)
    private Training trainingId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "startLatitude")
    private double startLatitude;

    @Column(name = "startLongitude")
    private double startLongitude;

    @Column(name = "endLatitude")
    private double endLatitude;

    @Column(name = "endLongitude")
    private double endLongitude;

    @Column(name = "altitude")
    private double altitude;

    public WorkoutSession(
        final Training trainingId,
        final Date timestamp,
        final double startLatitude,
        final double startLongitude,
        final double endLatitude,
        final double endLongitude,
        final double altitude) {

        this.trainingId = trainingId;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}
