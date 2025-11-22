package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_event")
@Data
@NoArgsConstructor
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private UserEvent event;

    @Column(name = "status")
    private String status;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
}