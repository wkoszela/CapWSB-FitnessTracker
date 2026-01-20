package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

/**
 * Encja reprezentująca pojedyncze trening użytkownika.
 * <p>
 * Zawiera informacje o konkretnym treningu, takie jak:
 * czas rozpoczęcia i zakończenia, typ aktywności, przebytą dystans i średnią prędkość.
 * </p>
 * <p>
 * Każde trening jest przypisane do konkretnego użytkownika (User)
 * i może mieć wiele sesji treningowych (WorkoutSession).
 * </p>
 * <p>
 * Typ aktywności (ActivityType) określa rodzaj wykonanej aktywności,
 * np. bieganie, rower, pływanie itp.
 * </p>
 *
 * @see User
 * @see ActivityType
 * @see pl.wsb.fitnesstracker.workoutsession.WorkoutSession
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /**
     * Unikalny identyfikator treningu.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Użytkownik, któremu przypisany jest trening.
     * Relacja wiele-do-jednego (ManyToOne).
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Czas rozpoczęcia treningu.
     * Pole wymagane.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * Czas zakończenia treningu.
     * Pole wymagane.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /**
     * Typ aktywności fizycznej (np. bieganie, rower, pływanie).
     * Pole wymagane, przechowywane jako typ wyliczeniowy (enum).
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /**
     * Dystans przebytego podczas treningu (w kilometrach).
     */
    @Column(name = "distance")
    private double distance;

    /**
     * Średnia prędkość podczas treningu (w km/h).
     */
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Konstruktor do tworzenia nowego treningu.
     *
     * @param user          użytkownik wykonujący trening
     * @param startTime     czas rozpoczęcia treningu
     * @param endTime       czas zakończenia treningu
     * @param activityType  typ aktywności
     * @param distance      przebytą odległość
     * @param averageSpeed  średnia prędkość
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    // Settery

    /**
     * Ustawia czas rozpoczęcia treningu.
     *
     * @param startTime Nowy czas rozpoczęcia
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Ustawia czas zakończenia treningu.
     *
     * @param endTime Nowy czas zakończenia
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Ustawia typ aktywności treningu.
     *
     * @param activityType Nowy typ aktywności
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Ustawia przebytą dystans.
     *
     * @param distance Nowa dystans (w km)
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Ustawia średnią prędkość treningu.
     *
     * @param averageSpeed Nowa średnia prędkość (w km/h)
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}