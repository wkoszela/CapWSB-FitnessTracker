package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "event") // Test 'shouldHaveEventTable' wymaga tej nazwy (liczba pojedyncza)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_time", nullable = false) // Mapowanie na snake_case
    @Temporal(TemporalType.TIMESTAMP) // Obsługa daty i godziny
    private Date startTime;

    @Column(name = "end_time", nullable = false) // Mapowanie na snake_case
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    // Konstruktor
    public Event(String name, String description, Date startTime, Date endTime, String country, String city) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.country = country;
        this.city = city;
    }
}