package pl.wsb.fitnesstracker.event.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.wsb.fitnesstracker.event.Event;

@Repository
public class EventRepository {

    @PersistenceContext
    private EntityManager entityManager;

    List<Event> findByName(String name) {
        String query = "SELECT e FROM Event e WHERE e.name = :name"; // Event to nazwa klasy encji, nie tabeli!

        return entityManager.createQuery(
                query, Event.class)
                .setParameter("name", name)
                .getResultList();
    }
}
