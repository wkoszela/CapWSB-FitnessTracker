package pl.wsb.fitnesstracker.userEvent;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import pl.wsb.fitnesstracker.event.Event;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "UserEvent")
@Getter
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "status")
    private String status;

    public UserEvent(final Long id, final User user, final Event event, final String status) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.status = status;
    }
}
