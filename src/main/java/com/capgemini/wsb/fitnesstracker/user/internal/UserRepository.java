package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.Comparator;
import org.springframework.stereotype.Repository;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    default Collection<User> findUserOlderThanX(int age) {
        return findAll().stream()
                .filter(user -> Objects.compare(user.getUserAge(), age, Comparator.naturalOrder()) > 0)
                .collect(Collectors.toList());
    }

}
