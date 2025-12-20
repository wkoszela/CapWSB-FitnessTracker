package pl.wsb.fitnesstracker.event.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.event.Event;

import java.util.List;

/**
 * Repository that provides custom queries for {@link Event} entities.
 *
 * <p>This repository is deliberately kept minimal – the standard CRUD
 * operations are already provided by Spring Data JPA.  If you need more
 * complex queries, consider extending {@code JpaRepository} or using a
 * dedicated query interface.</p>
 *
 * <h3>Example usage</h3>
 * <pre>{@code
 * @Autowired
 * private EventRepository eventRepo;
 *
 * List<Event> allSummerEvents = eventRepo.findByName("Summer Festival");
 * }</pre>
 *
 * @author Your Name
 * @since 1.0
 */
@Repository
public class EventRepository {

    /**
     * JPA {@link EntityManager} used for executing native and JPQL queries.
     */
    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * Constructor injection of the {@link EntityManager}.
     *
     * @param entityManager JPA entity manager
     */
    public EventRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds all {@link Event}s with the given name.
     *
     * <p>The query is written in JPQL – it selects from the {@code Event}
     * entity and filters by the {@code name} attribute.</p>
     *
     * @param name exact event name to search for
     * @return list of matching events; never {@code null}
     */
    List<Event> findByName(String name) {
        String query = "SELECT e FROM Event e WHERE e.name = :name";

        return entityManager.createQuery(query, Event.class)
                .setParameter("name", name)
                .getResultList();
    }
}
