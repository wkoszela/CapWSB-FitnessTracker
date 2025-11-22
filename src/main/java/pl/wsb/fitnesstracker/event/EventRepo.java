package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.entity.Event;

import java.util.List;

@Repository
public class EventRepo {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Event> findByName(String name) {
        String jpql = "SELECT * FROM Event WHERE Event.name = :eventName";

        TypedQuery<Event> query = entityManager.createQuery(jpql, Event.class);

        query.setParameter("eventName", name);

        return query.getResultList();
    }
}