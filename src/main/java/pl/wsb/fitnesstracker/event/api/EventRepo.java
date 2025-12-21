// The line `package pl.wsb.fitnesstracker.event.api;` in Java is declaring the package name for the
// current Java file. This helps organize classes into a specific namespace or package within the
// project structure. In this case, the class `EventRepo` belongs to the `api` package under
// `pl.wsb.fitnesstracker.event`. This naming convention helps in maintaining a structured codebase and
// avoids naming conflicts with classes from other packages.
// The line `package pl.wsb.fitnesstracker.event.api;` in Java is declaring the package name for the
// current Java file. This helps organize classes into a specific namespace or package within the
// project structure. In this case, the class `EventRepo` belongs to the `api` package under
// `pl.wsb.fitnesstracker.event`. This naming convention helps in maintaining a structured codebase and
// avoids naming conflicts with classes from other packages.
package pl.wsb.fitnesstracker.event.api;

import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.event.Event;

import java.util.List;

@Repository
public class EventRepo extends AbstractDao {

    public List<Event> findEventByName(String name) {
        String jpql = "SELECT e FROM Event e WHERE e.name = :name";

        return entityManager.createQuery(jpql, Event.class)
                .setParameter("name", name)
                .getResultList();
    }


}
