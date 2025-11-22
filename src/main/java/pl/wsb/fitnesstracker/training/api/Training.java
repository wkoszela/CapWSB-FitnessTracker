package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "distance", nullable = false)
    private double distance;

    @Column(name = "average_speed")
    @Nullable
    private Double averageSpeed;

    public Training(User user, Date startTime, Date endTime,
                   ActivityType activityType, double distance, Double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}