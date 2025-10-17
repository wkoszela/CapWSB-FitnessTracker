package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.event.Event;

@ToString
@NoArgsConstructor
@Getter
@Table(name="user_event")
@Entity
// TODO: Define the Event entity with appropriate fields and annotations
public class UserEvent {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Event event;

    private String status;
}
