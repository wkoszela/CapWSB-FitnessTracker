package pl.wsb.fitnesstracker.event;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository extends AbstractDao {

    public List<Event> findEventsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }

        return entityManager.createQuery(
                        "SELECT u FROM Event u WHERE LOWER(u.name) = LOWER(:name)", Event.class)
                .setParameter("name", name.trim())
                .getResultList();
    }
}
