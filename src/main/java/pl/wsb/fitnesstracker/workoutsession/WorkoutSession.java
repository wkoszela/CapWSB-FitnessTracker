package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.trainings.api.Trainings;

@Entity
@Table(name = "Workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Nullable
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", referencedColumnName = "id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name="fk_trainings_User"))
    private Trainings trainings;

    @Column(name = "timestamp", nullable = false,updatable = false)
    private String timestamp;

    @Column(name = "start_latitude",updatable = false)
    private double startLatitude;
    @Column(name = "start_longitude",updatable = false)
    private double startLongitude;
    @Column(name = "end_latitude",updatable = false)
    private double endLatitude;
    @Column(name = "end_longitude",updatable = false)
    private double endLongitude;
    @Column(name = "altitude",updatable = false)
    private double altitude;


    public WorkoutSession(Trainings trainings, String timestamp, double startLatitude, double startLongitude, double endLatitude, double endLongitude, double altitude) {
        this.trainings = trainings;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}
