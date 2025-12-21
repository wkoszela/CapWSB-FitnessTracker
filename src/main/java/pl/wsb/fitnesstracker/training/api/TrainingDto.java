package pl.wsb.fitnesstracker.training.api;

import lombok.Data;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.util.Date;

/**
 * Obiekt DTO (Data Transfer Object) reprezentujący trening.
 * Zawiera informacje o użytkowniku, czasie trwania, typie aktywności oraz
 * statystykach.
 */
@Data
public class TrainingDto {
    /**
     * Identyfikator treningu.
     */
    private Long id;
    /**
     * Uproszczone dane użytkownika przypisanego do treningu.
     */
    private UserSimpleDto user;
    /**
     * Czas rozpoczęcia treningu.
     */
    private Date startTime;
    /**
     * Czas zakończenia treningu.
     */
    private Date endTime;
    /**
     * Typ aktywności fizycznej (np. bieganie, pływanie).
     */
    private ActivityType activityType;
    /**
     * Dystans przebyty podczas treningu (w km).
     */
    private double distance;
    /**
     * Średnia prędkość podczas treningu (w km/h).
     */
    private double averageSpeed;
}