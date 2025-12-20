package pl.wsb.fitnesstracker.training.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) dla encji Training.
 *
 * Zawiera wszystkie informacje o treningu dla transferu danych HTTP.
 * Używana do komunikacji pomiędzy controllerem a klientem (API).
 *
 * @author Fitness Tracker Team
 */
public class TrainingDto {

    /**
     * Unikalny identyfikator treningu.
     * Null dla nowych treningów (przed zapisem do BD).
     */
    private Long id;

    /**
     * ID użytkownika wykonującego trening.
     */
    private Long userId;

    /**
     * Czas rozpoczęcia treningu.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startTime;

    /**
     * Czas zakończenia treningu.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date endTime;

    /**
     * Typ aktywności fizycznej (RUNNING, CYCLING, WALKING, SWIMMING, TENNIS).
     */
    private ActivityType activityType;

    /**
     * Przebyta dystans podczas treningu (w kilometrach).
     */
    private double distance;

    /**
     * Średnia prędkość podczas treningu (w km/h).
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
     * @param userId ID użytkownika
     * @param startTime Czas rozpoczęcia
     * @param endTime Czas zakończenia
     * @param activityType Typ aktywności
     * @param distance Dystans
     * @param averageSpeed Średnia prędkość
     */
    public TrainingDto(Long id, Long userId, Date startTime, Date endTime,
                      ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Konstruktor dla nowego treningu (bez ID).
     *
     * @param userId ID użytkownika
     * @param startTime Czas rozpoczęcia
     * @param endTime Czas zakończenia
     * @param activityType Typ aktywności
     * @param distance Dystans
     * @param averageSpeed Średnia prędkość
     */
    public TrainingDto(Long userId, Date startTime, Date endTime,
                      ActivityType activityType, double distance, double averageSpeed) {
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
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
                Objects.equals(userId, that.userId) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                activityType == that.activityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, startTime, endTime, activityType, distance, averageSpeed);
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", activityType=" + activityType +
                ", distance=" + distance +
                ", averageSpeed=" + averageSpeed +
                '}';
    }
}

