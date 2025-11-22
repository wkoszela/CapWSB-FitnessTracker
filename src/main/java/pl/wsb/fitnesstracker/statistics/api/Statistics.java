package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics") // Zmieniono na małe litery zgodnie z konwencją i testami
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TO JEST KLUCZOWE: Relacja OneToOne do użytkownika
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    @Column(name = "total_distance")
    private double totalDistance;

    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    // Opcjonalny konstruktor, jeśli chcesz go używać ręcznie, 
    // ale @AllArgsConstructor załatwia sprawę
    public Statistics(User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}