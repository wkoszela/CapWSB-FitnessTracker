package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.entity.Event;

import java.util.List;

@Repository
public class EventRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    public EventRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Event> findByName(String name) {
        String jpql = "SELECT * FROM Event WHERE Event.name = :eventName";

        TypedQuery<Event> query = entityManager.createQuery(jpql, Event.class);

        query.setParameter("eventName", name);

        return query.getResultList();
    }
}