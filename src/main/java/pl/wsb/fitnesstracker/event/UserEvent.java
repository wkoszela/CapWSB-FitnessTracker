package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "user_event") // Tabela łącząca
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "status")
    private String status;
}