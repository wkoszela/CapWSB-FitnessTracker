package pl.wsb.fitnesstracker.UserEvent;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "UserEvent")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "event_id")
    private Long event_id;

    @Column(name = "status")
    private int status;

    public UserEvent(@Nullable Long id, Long user_id, Long event_id, int status) {
        this.id = id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.status = status;
    }

}
