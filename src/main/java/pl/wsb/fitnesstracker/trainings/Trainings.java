package pl.wsb.fitnesstracker.trainings;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Trainings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name ="startTime", nullable = false)
    private Date startTime;

    @Column(name ="endTime", nullable = false)
    private Date endTime;

    @Column(name ="activityType")
    private ActivityType activityType;

    @Column(name ="distance")
    private float distance;

    @Column(name ="averageSpeed")
    private float averageSpeed;

    public Trainings(@Nullable Long id, User user_id, Date startTime, Date endTime, ActivityType activityType, float distance, float averageSpeed) {
        this.id = id;
        this.user = user_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }



}
