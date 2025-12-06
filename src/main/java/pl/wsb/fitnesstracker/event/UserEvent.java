package pl.wsb.fitnesstracker.event;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event eventid;

    @Column(name = "status")
    private String status;

    public UserEvent(
        
         
        String status,
        final User userid,
        final Event evetid 
        ) {
        this.status = status;
        
    }
}
