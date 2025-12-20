package pl.wsb.fitnesstracker.event;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Encja reprezentująca rejestrację użytkownika na wydarzenie.
 * <p>
 * Łączy użytkownika (User) z konkretnym wydarzeniem (Event) i przechowuje
 * informacje o statusie uczestnictwa użytkownika w danym wydarzeniu.
 * </p>
 * <p>
 * Relacja ma ograniczenie unikatowości - jeden użytkownik może być zarejestrowany
 * na konkretne wydarzenie tylko raz (kombinacja user_id i event_id musi być unikalna).
 * </p>
 *
 * @see User
 * @see Event
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "user_event",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvent {

    /**
     * Unikalny identyfikator rejestracji.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Użytkownik zarejestrowany na wydarzenie.
     * Relacja wiele-do-jednego (ManyToOne) - obowiązkowa.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Wydarzenie, na które zarejestrowany jest użytkownik.
     * Relacja wiele-do-jednego (ManyToOne) - obowiązkowa.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    /**
     * Status uczestnictwa użytkownika w wydarzeniu.
     * Pole opcjonalne, może przechowywać wartości takie jak:
     * "registered", "attended", "cancelled" itp.
     */
    @Column(name = "status")
    @Setter
    private String status;
}


