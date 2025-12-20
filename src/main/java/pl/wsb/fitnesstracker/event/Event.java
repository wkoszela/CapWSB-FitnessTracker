package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Encja reprezentująca wydarzenie w aplikacji Fitness Tracker.
 * <p>
 * Zawiera informacje o konkretnym wydarzeniu takie jak:
 * nazwa, opis, czas rozpoczęcia i zakończenia, lokalizacja (kraj i miasto).
 * </p>
 * <p>
 * Użytkownicy mogą rejestrować się na wydarzenia poprzez encję UserEvent,
 * która łączy użytkownika z konkretnym wydarzeniem.
 * </p>
 *
 * @see UserEvent
 * @see pl.wsb.fitnesstracker.user.api.User
 *
 * @author Fitness Tracker Team
 */
@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    /**
     * Unikalny identyfikator wydarzenia.
     * Generowany automatycznie przez bazę danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa wydarzenia.
     * Pole wymagane.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Opis wydarzenia.
     * Pole opcjonalne, przechowywane jako tekst o zmiennej długości.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Data i czas rozpoczęcia wydarzenia.
     * Pole wymagane.
     */
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    /**
     * Data i czas zakończenia wydarzenia.
     * Pole wymagane.
     */
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    /**
     * Kraj, w którym odbywa się wydarzenie.
     * Pole opcjonalne.
     */
    @Column(name = "country")
    private String country;

    /**
     * Miasto, w którym odbywa się wydarzenie.
     * Pole opcjonalne.
     */
    @Column(name = "city")
    private String city;

}
