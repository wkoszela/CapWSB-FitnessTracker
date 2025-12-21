// The code snippet provided is a Java interface named `WorkoutSessionCustomRepository` that defines a
// method `findSessionsByTrainingId` for executing a custom JPQL query to find workout sessions related
// to a specific training ID within a fitness tracker application. The interface specifies that the
// method should return a list of `WorkoutSession` objects and takes a `Long` parameter `trainingId` to
// search for sessions related to that specific training ID.
/**
 * The `WorkoutSessionCustomRepositoryImpl` class implements an interface to execute a custom JPQL
 * query for finding workout sessions related to a specific training ID within a fitness tracker
 * application.
 */
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