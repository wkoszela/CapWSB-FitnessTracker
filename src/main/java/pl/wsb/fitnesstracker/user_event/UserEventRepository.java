package pl.wsb.fitnesstracker.user_event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.statistics.api.Statistics;

import java.util.List;

@Repository
public class UserEventRepository {

    /**
     * Klasa odpowiadająca za zapytania SQL do  tabeli UserEvent
     */
    @PersistenceContext
    private EntityManager entityManager;

    List<UserEvent> findByUserId(Long userId){
        /**
        Zapytanie o userevent danego usera
         */
        return entityManager.createQuery(
                        "SELECT ue FROM UserEvent ue WHERE ue.user.id = :userId",
                        UserEvent.class
                ).setParameter("userId", userId)
                .getResultList();
    }
}
