    package pl.wsb.fitnesstracker.event;

    import org.springframework.stereotype.Repository;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.PersistenceContext;
    import java.util.List;

    @Repository
    public class EventRepository {

        @PersistenceContext
        private EntityManager entityManager;

        public List<Event> findByName(String name) {
            String jpql = "SELECT e FROM Event e WHERE e.name = :name";

            return entityManager.createQuery(jpql, Event.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }