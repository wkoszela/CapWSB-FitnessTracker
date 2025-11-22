package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "UserEvent")
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    private Date endTime;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    public Event(final Long id,final String name,final String description,final Date startTime,final Date endTime,final String country,final String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.country = country;
        this.city = city;
    }
}
