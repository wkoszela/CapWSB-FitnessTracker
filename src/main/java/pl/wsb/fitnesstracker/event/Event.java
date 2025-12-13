package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "Event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString

// TODO: Define the Event entity with appropriate fields and annotations
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;


    @Column(name = "name")
    private String name; 

    @Column(name = "description")
    private String description; 

    @Column(name = "startTime")
    private String startTime; 

    @Column(name = "endTime")
    private String endTime; 

    @Column(name = "country")
    private String country;
    
    @Column(name = "city")
    private String city;
    

    public Event(
        


        String name,
        String description,
        String startTime,
        String endTime,
        String country,
        final String city
        ) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.country = country;
        this.city = city;
    }
}
