package pl.wsb.fitnesstracker.event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    /**
     * Klasa odpowiadająca za zapytania SQL do
     */
    @PersistenceContext
    private EntityManager entityManager;

    public List<Event> findEventsInPoland() {
        /**
         * Zapytanie o eventy z Polski
         */
        return entityManager.createQuery(
                        "SELECT e FROM Event e WHERE e.country = :country",
                        Event.class
                ).setParameter("country", "Polska")
                .getResultList();
    }
}
