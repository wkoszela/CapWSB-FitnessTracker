package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;

@Entity
@Table(name = "workout_sessions") // Zmiana na liczbę mnogą, by pasowało do testów
@Getter
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Zmieniono int na Long (standard JPA)

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training; // Zmieniono nazwę zmiennej na małą literę

    private Date timestamp; // Zmieniono String na Date

    @Column(name = "start_latitude")
    private double startLatitude;

    @Column(name = "start_longitude")
    private double startLongitude;

    @Column(name = "end_latitude")
    private double endLatitude;

    @Column(name = "end_longitude")
    private double endLongitude;

    private double altitude;

    // Usunąłem ręczny konstruktor, który powodował błędy kompilacji.
    // Dzięki @NoArgsConstructor Hibernate poradzi sobie z tworzeniem obiektu.
    // Jeśli potrzebujesz konstruktora do testów, możesz użyć @AllArgsConstructor z Lomboka
    // lub stworzyć taki, który przyjmuje obiekt Training, a nie int trainingId.
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