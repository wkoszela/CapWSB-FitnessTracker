package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User; // Musisz zaimportować User

/**
 * Encja reprezentująca statystyki użytkownika.
 */
@Entity
@Table(name = "statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "totalTrainings", nullable = false)
    private int totalTrainings;

    @Column(name = "totalDistance")
    private double totalDistance;

    @Column(name = "totalCaloriesBurned")
    private int totalCaloriesBurned;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * Konstruktor tworzący statystyki.
     *
     * @param totalTrainings      całkowita liczba treningów
     * @param totalDistance       całkowity dystans
     * @param totalCaloriesBurned całkowita liczba spalonych kalorii
     * @param user                użytkownik, do którego należą statystyki
     */
    public Statistics(int totalTrainings, double totalDistance, int totalCaloriesBurned, User user) {
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.user = user;
    }
}