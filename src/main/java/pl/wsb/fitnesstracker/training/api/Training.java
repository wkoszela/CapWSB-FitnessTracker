package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "trainings")
@Data
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;


    public Training(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.user = user;

        this.startTime = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.endTime = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}