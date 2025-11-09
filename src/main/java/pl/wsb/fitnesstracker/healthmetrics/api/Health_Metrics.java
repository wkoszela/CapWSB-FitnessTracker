package pl.wsb.fitnesstracker.healthmetrics.api;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "Health_Metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Health_Metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name ="height")
    private Double height;

    @Column(name = "heartRate")
    private int heart_Rate;


}
