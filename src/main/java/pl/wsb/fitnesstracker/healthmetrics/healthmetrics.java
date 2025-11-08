package pl.wsb.fitnesstracker.healthmetrics;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "heath_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class healthmetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userid;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = false)
    private Long weight;

    @Column(name = "height", nullable = false)
    private Long height;

    @Column(name = "heartRate", nullable = false)
    private Long heartRate;

    public healthmetrics(
            final User user_id,
            final Long lastName,
            final LocalDate date,
            final Long weight,
            final Long height,
            final Long heartRate) {

        this.date = date;
    }
}
