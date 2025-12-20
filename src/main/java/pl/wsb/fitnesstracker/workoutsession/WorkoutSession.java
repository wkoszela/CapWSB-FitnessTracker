package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;

/**
 * Encja reprezentująca pojedynczą sesję treningową.
 * <p>
 * Zawiera szczegółowe informacje geograficzne i czasowe dla konkretnej sesji treningu,
 * takie jak: współrzędne geograficzne (szerokość i długość geograficzna),
 * wysokość oraz dokładny czas sesji.
 * </p>
 * <p>
 * Każda sesja treningowa jest przypisana do konkretnego treningu (Training).
 * Pojedyncze trening może mieć wiele sesji treningowych.
 * </p>
 * <p>
 * Sesja treningowa zawiera:
 * - Punkt startowy (start_latitude, start_longitude)
 * - Punkt końcowy (end_latitude, end_longitude) - opcjonalnie
 * - Wysokość (altitude) - opcjonalnie
 * - Znacznik czasowy (timestamp)
 * </p>
 *
 * @see Training
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSession {

    /**
     * Unikalny identyfikator sesji treningowej.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Trening, do którego przypisana jest ta sesja treningowa.
     * Relacja wiele-do-jednego (ManyToOne) - obowiązkowa.
     * Zaladowanie danych jest leniwe (LAZY).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    /**
     * Data i czas sesji treningowej.
     * Pole wymagane.
     */
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    /**
     * Szerokość geograficzna punktu startowego.
     * Pole wymagane.
     */
    @Column(name = "start_latitude", nullable = false)
    private double startLatitude;

    /**
     * Długość geograficzna punktu startowego.
     * Pole wymagane.
     */
    @Column(name = "start_longitude", nullable = false)
    private double startLongitude;

    /**
     * Szerokość geograficzna punktu końcowego.
     * Pole opcjonalne.
     */
    @Column(name = "end_latitude")
    private Double endLatitude;

    /**
     * Długość geograficzna punktu końcowego.
     * Pole opcjonalne.
     */
    @Column(name = "end_longitude")
    private Double endLongitude;

    /**
     * Wysokość n.p.m. w metrach.
     * Pole opcjonalne.
     */
    @Column(name = "altitude")
    private Double altitude;

}
