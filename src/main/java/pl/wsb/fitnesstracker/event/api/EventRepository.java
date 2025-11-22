package pl.wsb.fitnesstracker.event.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.wsb.fitnesstracker.event.Event;

@Repository
public class EventRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public EventRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    List<Event> findByName(String name) {
        String query = "SELECT e FROM Event e WHERE e.name = :name";

        return entityManager.createQuery(
                query, Event.class)
                .setParameter("name", name)
                .getResultList();
    }
}
