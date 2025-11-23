package pl.wsb.fitnesstracker.trainings;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
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

    @Column(name ="user_id")
    private int user_id;

    @Column(name ="startTime")
    private Date startTime;

    @Column(name ="endTime")
    private Date endTime;

    @Column(name ="activityType")
    private ActivityType activityType;

    @Column(name ="distance")
    private float distance;

    @Column(name ="averageSpeed")
    private float averageSpeed;

    public Trainings(@Nullable Long id, int user_id, Date startTime, Date endTime, ActivityType activityType, float distance, float averageSpeed) {
        this.id = id;
        this.user_id = user_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }



}
