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
