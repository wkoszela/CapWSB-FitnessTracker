/**
 * The `Statistics` class in the fitness tracker API represents user statistics including total
 * trainings, distance, calories burned, and has a one-to-one relationship with the `User` class.
 */
package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User; // Musisz zaimportować User

@Entity
@Table(name = "statistics") // POPRAWIONA NAZWA TABELI (zgodnie z testem i schematem)
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

    // --- NOWA RELACJA (Jednostronna OneToOne) ---
    // Realizuje polecenie i jest zgodna ze schematem (user_id)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    // @JoinColumn tworzy kolumnę `user_id`, której wymaga test
    // `unique = true` zapewnia, że jest to relacja 1-do-1 (jeden user ma jedne statystyki)
    private User user;
    // --- KONIEC NOWEJ RELACJI ---

    public Statistics(int totalTrainings, double totalDistance, int totalCaloriesBurned, User user) {
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
        this.user = user; // Zaktualizowany konstruktor
    }
}