package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;

import java.util.Date;

// TODO: Define the Event entity with appropriate fields and annotations
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private String country;

    private String city;

    public Event(String name, String description, Date startTime, Date endTime, String country, String city){
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.country = country;
        this.city = city;

    }
}
