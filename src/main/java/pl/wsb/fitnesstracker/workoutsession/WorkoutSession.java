package pl.wsb.fitnesstracker.workoutsession;

import pl.wsb.fitnesstracker.training.api.Training; // Import encji Training

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date; // Używamy Date dla spójności z Training

@Entity
@Table(name = "workout_session") // Wymagane przez test
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Zmieniamy int na Long (standard w JPA)

    // Zamiast 'int trainingId', robimy relację do obiektu Training
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false) // To stworzy kolumnę 'training_id'
    private Training training;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp; // Zmieniamy String na Date

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

    // Konstruktor
    public WorkoutSession(Training training, Date timestamp, double startLatitude, double startLongitude, double endLatitude, double endLongitude, double altitude) {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}