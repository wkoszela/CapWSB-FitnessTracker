package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "training_id")
    private int trainingId;
    private String timestamp;
    @Column(name = "start_latitude")
    private double startLatitude;
    @Column(name = "start_longitude")
    private double startLongitude;
    @Column(name = "end_latitude")
    private double endLatitude;
    @Column(name = "end_longitude")
    private double endLongitude;

    private double altitude;

    public WorkoutSession(double endLongitude, int id, int trainingId, String timestamp, double startLatitude, double startLongitude, double endLatitude, double altitude) {
        this.endLongitude = endLongitude;
        this.id = id;
        this.trainingId = trainingId;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.altitude = altitude;
    }
}
