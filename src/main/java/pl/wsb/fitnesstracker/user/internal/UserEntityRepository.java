package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserEntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findUsersWithEmailEndingWith(LocalDate date) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.birthday > :date",
                        User.class
                ).setParameter("date", date)
                .getResultList();
    }
}
