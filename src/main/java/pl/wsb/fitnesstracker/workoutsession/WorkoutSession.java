package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WorkoutSession {

    @Id
    private int id;
    private int trainingId;
    private String timestamp;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private double altitude;
    }
