// The line `package pl.wsb.fitnesstracker.event;` in Java is declaring the package name for the
// `Event` class. In Java, packages are used to organize classes into namespaces, making it easier to
// manage and locate related classes. In this case, the `Event` class is part of the
// `pl.wsb.fitnesstracker.event` package, which indicates its location within the project structure.
package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private String city;

    private String country;

    public Event(String name, LocalDateTime startTime, String city) {
        this.name = name;
        this.startTime = startTime;
        this.city = city;
    }
}