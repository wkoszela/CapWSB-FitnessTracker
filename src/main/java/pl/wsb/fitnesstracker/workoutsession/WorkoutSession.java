package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_id", nullable = false)
    private Integer trainingId;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @Column(name = "start_latitude", nullable = false)
    private double startLatitude;

    @Column(name = "start_longitude", nullable = false)
    private double startLongitude;

    @Column(name = "end_latitude", nullable = false)
    private double endLatitude;

    @Column(name = "end_longitude", nullable = false)
    private double endLongitude;

    @Column(name = "altitude", nullable = false)
    private double altitude;

    public WorkoutSession(
            final Integer trainingId,
            final String timestamp,
            final double startLatitude,
            final double startLongitude,
            final double endLatitude,
            final double endLongitude,
            final double altitude
    ) {
        this.trainingId = trainingId;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}
