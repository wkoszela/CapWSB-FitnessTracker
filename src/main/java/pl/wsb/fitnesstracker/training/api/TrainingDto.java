package pl.wsb.fitnesstracker.training.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) dla encji Training.
 * <p>
 * Zawiera wszystkie informacje o treningu dla transferu danych HTTP.
 * Używana do komunikacji pomiędzy controllerem a klientem (API).
 * Zapewnia hermetyzację danych encji i umożliwia kontrolę nad formatowaniem
 * dat w komunikacji JSON.
 * </p>
 * <p>
 * Obydwie konstruktory (z ID i bez ID) umożliwiają elastyczną tworzenie instancji
 * zarówno do deserializacji JSON jak i tworzenia nowych treningów.
 * </p>
 *
 * @see Training
 * @see ActivityType
 *
 * @author Fitness Tracker Team
 */
public class TrainingDto {

    /**
     * Unikalny identyfikator treningu.
     * Null dla nowych treningów (przed zapisem do BD).
     * Generowany automatycznie przez bazę danych.
     */
    private Long id;

    /**
     * Użytkownik wykonujący trening.
     * Zagnieżdżony obiekt User zawierający pełne dane użytkownika.
     * Pole wymagane - określa, do którego użytkownika należy trening.
     */
    private UserDto user;

    /**
     * Czas rozpoczęcia treningu.
     * Format JSON: "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00" (np. 2024-01-15T10:30:00.000+00:00)
     * Pole wymagane.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00")
    private Date startTime;

    /**
     * Czas zakończenia treningu.
     * Format JSON: "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00" (np. 2024-01-15T11:45:00.000+00:00)
     * Pole wymagane. Musi być późniejszy niż startTime.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00")
    private Date endTime;

    /**
     * Typ aktywności fizycznej.
     * Możliwe wartości: RUNNING, CYCLING, WALKING, SWIMMING, TENNIS
     * Pole wymagane.
     */
    private ActivityType activityType;

    /**
     * Przebyta dystans podczas treningu (w kilometrach).
     * Pole opcjonalne, wartość domyślna to 0.0.
     */
    private double distance;

    /**
     * Średnia prędkość podczas treningu (w km/h).
     * Pole opcjonalne, wartość domyślna to 0.0.
     */
    private double averageSpeed;

    /**
     * Konstruktor domyślny (pusty).
     * Wymagany do deserializacji JSON.
     */
    public TrainingDto() {
    }

    /**
     * Konstruktor z parametrami.
     *
     * @param id ID treningu
     * @param user Użytkownik wykonujący trening
     * @param startTime Czas rozpoczęcia
     * @param endTime Czas zakończenia
     * @param activityType Typ aktywności
     * @param distance Dystans
     * @param averageSpeed Średnia prędkość
     */
    public TrainingDto(Long id, UserDto user, Date startTime, Date endTime,
                      ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Konstruktor dla nowego treningu (bez ID).
     *
     * @param user Użytkownik wykonujący trening
     * @param startTime Czas rozpoczęcia
     * @param endTime Czas zakończenia
     * @param activityType Typ aktywności
     * @param distance Dystans
     * @param averageSpeed Średnia prędkość
     */
    public TrainingDto(UserDto user, Date startTime, Date endTime,
                      ActivityType activityType, double distance, double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    // Gettery

    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public double getDistance() {
        return distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    // Settery

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingDto that = (TrainingDto) o;
        return Double.compare(that.distance, distance) == 0 &&
                Double.compare(that.averageSpeed, averageSpeed) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                activityType == that.activityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, startTime, endTime, activityType, distance, averageSpeed);
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "id=" + id +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", activityType=" + activityType +
                ", distance=" + distance +
                ", averageSpeed=" + averageSpeed +
                '}';
    }
}

