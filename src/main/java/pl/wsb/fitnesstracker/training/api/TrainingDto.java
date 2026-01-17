package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import java.util.Date;

/**
 * Data Transfer Object for {@link pl.wsb.fitnesstracker.training.api.Training}.
 *
 * <p>The DTO is used in all controller endpoints.  It contains a nested
 * {@link UserDto} to avoid sending the full {@code User} entity over HTTP.</p>
 *
 * @author Your Name
 */
public class TrainingDto {

    /** Primary key of the training record (may be {@code null} when creating a new one). */
    private Long id;

    /** User information.  Only the fields that are relevant for the client are exposed. */
    private UserDto user;

    /** Training start timestamp. */
    private Date startTime;

    /** Training end timestamp. */
    private Date endTime;

    /** Activity type (enum stored as a string in the database). */
    private ActivityType activityType;

    /** Distance covered in kilometres. */
    private double distance;

    /** Average speed in km/h. */
    private double averageSpeed;

    /** Default constructor required by Jackson. */
    public TrainingDto() {
    }

    /**
     * Full‑constructor.
     *
     * @param id           the training identifier
     * @param user         the user performing the training
     * @param startTime    when the training started
     * @param endTime      when it finished
     * @param activityType type of activity
     * @param distance     total distance (km)
     * @param averageSpeed mean speed (km/h)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
    
    /**
     * Lightweight representation of a {@link pl.wsb.fitnesstracker.user.api.User}
     * that is sent to the client.  The full user entity is not required by
     * any controller endpoint.
     */
    public static class UserDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;

        public UserDto() {
        }

        public UserDto(Long id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
