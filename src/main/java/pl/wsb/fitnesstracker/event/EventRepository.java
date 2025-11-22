package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Event> findEventsByCountry(String country) {
        String jpql = "SELECT e FROM Event e WHERE e.country = :country";
        return entityManager.createQuery(jpql, Event.class)
                .setParameter("country", country)
                .getResultList();
    }
}