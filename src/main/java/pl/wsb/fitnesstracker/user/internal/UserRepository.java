package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches partially and is case-insensitive.
     *
     * @param email partial email of the users to search
     * @return list of matching users' IDs and emails
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Find users older than specified age.
     *
     * @param age minimum age of users to find
     * @return list of users older than specified age
     */
    default List<User> findOlderThan(int age) {
        LocalDate date = LocalDate.now().minusYears(age);
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }

    /**
     * Find users by their first and last name.
     *
     * @param firstName first name to search for
     * @param lastName  last name to search for
     * @return list of users matching the given first and last name
     */
    default List<User> findUsersByFullName(String firstName, String lastName) {
        return findAll().stream()
                .filter(user -> user.getLastName().equals(lastName))
                .filter(user -> user.getFirstName().equals(firstName))
                .toList();
    }

    /**
     * Find users by their birth date.
     *
     * @param birthDate birth date to search for
     * @return list of users with matching birth date
     */
    default List<User> findByBirthDate(LocalDate birthDate) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().equals(birthDate))
                .toList();
    }

    /**
     * Search users by multiple optional criteria.
     * Returns users matching all provided non-null criteria.
     *
     * @param id        user ID to search for (optional)
     * @param fullName  full name to search for, case insensitive (optional)
     * @param birthDate birth date as string to search for (optional)
     * @param email     email to search for, case insensitive (optional)
     * @return list of users matching all provided criteria
     */
    default List<User> search(Long id, String fullName, String birthDate, String email) {
        return findAll().stream()
                .filter(user -> id == null || user.getId().equals(id))
                .filter(user -> fullName == null || (user.getFirstName() + " " + user.getLastName()).equalsIgnoreCase(fullName))
                .filter(user -> birthDate == null || user.getBirthdate().toString().equals(birthDate))
                .filter(user -> email == null || user.getEmail().equalsIgnoreCase(email))
                .toList();
    }


}
