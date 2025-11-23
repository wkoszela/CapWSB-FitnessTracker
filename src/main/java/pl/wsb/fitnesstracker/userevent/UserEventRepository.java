package pl.wsb.fitnesstracker.userevent;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserEventRepository {

    @PersistenceContext
    private EntityManager em;

    public List<UserEvent> findByUserId(Long userId) {
        TypedQuery<UserEvent> q = em.createQuery(
                "SELECT ue FROM UserEvent ue WHERE ue.user.id = :userId", UserEvent.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }
}
