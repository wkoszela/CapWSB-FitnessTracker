package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;

@Entity
@Table(name = "Event ")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
// TODO: Define the Event entity with appropriate fields and annotations
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name ="name", nullable = false)
    private String name;

    @Column(name ="description", nullable = false)
    private String description;

    @Column(name ="StartTime", nullable = false)
    private LocalDate StartTime;

    @Column(name ="EndTime", nullable = false)
    private LocalDate EndTime;

    @Column(name ="Country", nullable = false)
    private String Country;

    @Column(name ="City", nullable = false)
    private String City;


    public Event(@Nullable Long id, String name, String description, LocalDate startTime, LocalDate endTime, String country, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        StartTime = startTime;
        EndTime = endTime;
        Country = country;
        City = city;
    }
}
