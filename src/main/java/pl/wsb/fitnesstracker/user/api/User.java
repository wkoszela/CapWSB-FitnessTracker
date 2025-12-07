package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.healthmetrics.HealthMetrics; // Musisz zaimportować nową klasę

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users") // Zgodnie ze schematem i testem
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    // --- NOWE POLA ---
    // Dodane, aby pasowały do schematu db_schema.png
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;
    // --- KONIEC NOWYCH PÓL ---

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    // --- NOWA RELACJA (Dwukierunkowa) ---
    // Realizuje relację 1-do-wielu z HealthMetrics
    // "mappedBy" mówi, że pole "user" w klasie HealthMetrics zarządza tą relacją
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<HealthMetrics> healthMetrics = new HashSet<>();
    // --- KONIEC NOWEJ RELACJI ---

    // Zgodnie z wymogiem relacji JEDNOSTRONNEJ OneToOne (User <-> Statistics),
    // celowo nie dodajemy tutaj pola `private Statistics statistics;`

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {
        // Zaktualizowany konstruktor
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    // Metoda pomocnicza do zarządzania relacją dwukierunkową
    public void addHealthMetrics(HealthMetrics metric) {
        this.healthMetrics.add(metric);
        metric.setUser(this);
    }
}