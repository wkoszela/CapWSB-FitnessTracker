// The line `package pl.wsb.fitnesstracker.event.api;` in Java is declaring the package name for the
// current Java file. In this case, the file belongs to the package `pl.wsb.fitnesstracker.event.api`.
// Packages are used in Java to organize classes and interfaces into namespaces, making it easier to
// manage and locate related code.
package pl.wsb.fitnesstracker.event.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class AbstractDao {

    @PersistenceContext
    protected EntityManager entityManager;

}