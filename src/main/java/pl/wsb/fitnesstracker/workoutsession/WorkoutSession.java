package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "WorkoutSession")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingId", nullable = false)
    private Training training;

    private String timestamp;

    private double startLatitude;

    private double startLongitude;

    private double endLatitude;

    private double endLongitude;

    private double altitude;

    public WorkoutSession(Training training, String timestamp, double startLatitude, double startLongitude, double endLatitude,
                          double endLongitude, double altitude){
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;

    }
}
