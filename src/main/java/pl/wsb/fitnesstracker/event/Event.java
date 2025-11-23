package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Nullable
    private Long id;

    @Column(nullable = false,updatable = false)
    private String name;

    private String description;

    @Column(nullable = false,updatable = false)
    private Date startTime;

    @Column(nullable = false,updatable = false)
    private Date endTime;

    @Column(nullable = false,updatable = false)
    private String country;

    @Column(nullable = false,updatable = false)
    private String city;


    public Event(String name, Date endTime, Date startTime, String city, String country) {
        this.name = name;
        this.endTime = endTime;
        this.startTime = startTime;
        this.city = city;
        this.country = country;
    }
}
