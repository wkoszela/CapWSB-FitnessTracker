package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "event") // Test wymaga nazwy "event" (liczba pojedyncza)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private String country;
    private String city;
}