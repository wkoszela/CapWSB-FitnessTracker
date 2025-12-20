package pl.wsb.fitnesstracker.event.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstrakcyjna klasa bazowa dla DAO (Data Access Object) operacji na encjach.
 * <p>
 * Dostarcza wspólny dostęp do EntityManager dla wszystkich podklas.
 * Pozwala na bezpośrednie wykonywanie JPQL queries przy użyciu EntityManager.
 * </p>
 * <p>
 * Wszystkie operacje w tej klasie są transakcyjne (marked with @Transactional).
 * Każda podklasa może wstrzyknąć EntityManager i używać go do wykonywania
 * custom operacji na bazie danych.
 * </p>
 *
 * @author Fitness Tracker Team
 */
@Transactional
public abstract class AbstractDao {

    @PersistenceContext
    protected EntityManager entityManager;

}