package com.capgemini.wsb.fitnesstracker.loader;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDate.now;
import static java.util.Objects.isNull;

/**
 * Sample init data loader. If the application is run with `loadInitialData` profile, then on application startup it will fill the database with dummy data,
 * for the manual testing purposes. Loader is triggered by {@link ContextRefreshedEvent } event
 */
@Component
@Profile("loadInitialData")
@Slf4j
@ToString
class InitialDataLoader {

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @EventListener
    @Transactional
    @SuppressWarnings({"squid:S1854", "squid:S1481", "squid:S1192", "unused"})
    public void loadInitialData(ContextRefreshedEvent event) {
        verifyDependenciesAutowired();

        log.info("Loading initial data to the database");

        User user1 = privateUser("Emma", "Johnson", 28);
        User user2 = privateUser("Ethan", "Taylor", 51);
        User user3 = privateUser("Olivia", "Davis", 76);
        User user4 = privateUser("Daniel", "Thomas", 34);
        User user5 = privateUser("Sophia", "Baker", 49);
        User user6 = privateUser("Liam", "Jones", 23);
        User user7 = privateUser("Ava", "Williams", 21);
        User user8 = privateUser("Noah", "Miller", 39);
        User user9 = privateUser("Grace", "Anderson", 33);
        User user10 = privateUser("Oliver", "Swift", 29);
        User user11 = privateUser("Ella", "Harris", 42);

        log.info("Finished loading initial data");
    }

    private User privateUser(String name, String lastName, int age) {
        User user = new User(name,
                             lastName,
                             now().minusYears(age),
                             "%s.%s@domain.com".formatted(name, lastName));
        return userRepository.save(user);
    }

    private void verifyDependenciesAutowired() {
        if (isNull(userRepository)) {
            throw new IllegalStateException("Initial data loader was not autowired correctly " + this);
        }
    }

}
