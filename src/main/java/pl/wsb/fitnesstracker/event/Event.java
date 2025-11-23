package pl.wsb.fitnesstracker.event;


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
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;
}
