package pl.wsb.fitnesstracker.training.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Getter
@Entity
@Table(name = "Trainings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString

public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    
    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "ActivityType")
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double average_speed;

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
        this.average_speed = average_speed;
    }

}