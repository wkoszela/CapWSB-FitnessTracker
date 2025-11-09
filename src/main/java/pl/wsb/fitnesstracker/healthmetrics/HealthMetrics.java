package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "health_metrics") // Zgodnie ze schematem i testem
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pola zgodne ze schematem db_schema.png
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private int heartRate;

    // --- NOWA RELACJA (ManyToOne) ---
    // Realizuje polecenie i jest zgodna ze schematem (user_id)
    // Wiele wpisów "HealthMetrics" może należeć do jednego "Usera"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    // @JoinColumn tworzy kolumnę `user_id`, której wymaga test
    private User user;
    // --- KONIEC NOWEJ RELACJI ---

    public HealthMetrics(LocalDate date, double weight, int heartRate, User user) {
        this.date = date;
        this.weight = weight;
        this.heartRate = heartRate;
        this.user = user;
    }
}