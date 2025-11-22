package pl.wsb.fitnesstracker.workoutsession;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "WorkoutSession")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString

// TODO: Define the Event entity with appropriate fields and annotations
public class WorkoutSession {

    @Id

    @Column(name = "id")
    private int id;
    @Column(name = "trainingId")
    private int trainingId;
    @Column(name = "timestamp")
    private String timestamp;
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
        



        int id,
        int trainingId,
        String timestamp,
        double startLatitude,
        double startLongitude,
        double endLatitude,
        double endLongitude,
        double altitude
        ) {
        this.id = id;
        this.trainingId = trainingId; 
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
        
    }



}
