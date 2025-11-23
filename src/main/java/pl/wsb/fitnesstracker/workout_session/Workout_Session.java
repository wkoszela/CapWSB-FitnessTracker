package pl.wsb.fitnesstracker.workout_session;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.trainings.Trainings;

import java.time.LocalDate;

@Entity
@Table(name = "Workout_Session ")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Workout_Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    private Trainings training_id;

    @Column(name = "timestamp",nullable = false, unique = true)
    private LocalDate timestamp;

    @Column(name = "startLatitude",nullable = false, unique = true)
    private Float startLatitude;

    @Column(name = "startLongitude",nullable = false, unique = true)
    private Float startLongitude;

    @Column(name = "endLatitude",nullable = false, unique = true)
    private Float endLatitude;

    @Column(name = "endLongitude",nullable = false, unique = true)
    private Float endLongitude;

    @Column(name = "altitude",nullable = false, unique = true)
    private Float altitude;

    public Workout_Session(@Nullable Long id, Trainings training_id, LocalDate timestamp, Float startLatitude, Float startLongitude, Float endLatitude, Float endLongitude, Float altitude) {
        this.id = id;
        this.training_id = training_id;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}
