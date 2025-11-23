package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {
    @PersistenceContext
    private EntityManager en;

    public EventRepository(EntityManager en) {
        this.en = en;
    }

    private List<Event> getEventByName(String name) {
        String jpql = "SELECT * FROM Event e WHERE e.name = :name";

        TypedQuery<Event> query = en.createQuery(jpql, Event.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
