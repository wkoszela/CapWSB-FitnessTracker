package pl.wsb.fitnesstracker.training.internal;

import java.util.Objects;

/**
 * Uproszczony Data Transfer Object (DTO) dla encji Training.
 *
 * Zawiera tylko podstawowe informacje o treningu (ID i typ aktywności).
 * Używana do zwracania uproszczonej listy treningów.
 *
 * @author Fitness Tracker Team
 */
public class TrainingSimpleDto {

    /**
     * Unikalny identyfikator treningu.
     */
    private Long id;

    /**
     * ID użytkownika wykonującego trening.
     */
    private Long userId;

    /**
     * Typ aktywności fizycznej.
     */
    private ActivityType activityType;

    /**
     * Konstruktor domyślny (pusty).
     */
    public TrainingSimpleDto() {
    }

    /**
     * Konstruktor z parametrami.
     *
     * @param id ID treningu
     * @param userId ID użytkownika
     * @param activityType Typ aktywności
     */
    public TrainingSimpleDto(Long id, Long userId, ActivityType activityType) {
        this.id = id;
        this.userId = userId;
        this.activityType = activityType;
    }

    // Gettery

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    // Settery

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingSimpleDto that = (TrainingSimpleDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                activityType == that.activityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, activityType);
    }

    @Override
    public String toString() {
        return "TrainingSimpleDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", activityType=" + activityType +
                '}';
    }
}

