package pl.wsb.fitnesstracker.event;

import pl.wsb.fitnesstracker.user.api.User; // Import encji User
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_event") // Test 'shouldHaveUserEventTable' wymaga tej nazwy
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacja do Usera (klucz obcy user_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacja do Eventu (klucz obcy event_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // Pole status (wymagane przez test)
    // Może to być String lub Enum, na potrzeby testu schematu String wystarczy
    @Column(nullable = false)
    private String status;

    // Konstruktor
    public UserEvent(User user, Event event, String status) {
        this.user = user;
        this.event = event;
        this.status = status;
    }
}