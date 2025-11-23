package pl.wsb.fitnesstracker.userevent;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "status", nullable = false)
    private String status;

    public UserEvent(
            final Long userId,
            final Long eventId,
            final String status
    ) {
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
    }
}
