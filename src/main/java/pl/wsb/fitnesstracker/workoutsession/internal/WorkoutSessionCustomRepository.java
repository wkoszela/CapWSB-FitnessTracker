package pl.wsb.fitnesstracker.workoutsession.internal;

import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;
import java.util.List;

public interface WorkoutSessionCustomRepository {
    /**
     * Metoda realizująca punkt 5 LAB03 (własne zapytanie JPQL).
     * Wyszukuje sesje treningowe powiązane z konkretnym treningiem.
     */
    List<WorkoutSession> findSessionsByTrainingId(Long trainingId);
}