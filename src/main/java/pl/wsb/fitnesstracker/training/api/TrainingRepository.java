// This code snippet is defining a Java interface named `TrainingRepository` that extends
// `JpaRepository`. The `JpaRepository` interface is provided by the Spring Data JPA framework and is
// used for creating repositories that interact with a database using JPA (Java Persistence API). In
// this case, the `TrainingRepository` interface is specifying that it will handle entities of type
// `Training` with an ID of type `Long`. This interface will provide methods for basic CRUD (Create,
// Read, Update, Delete) operations on the `Training` entity. The package declaration `package
// pl.wsb.fitnesstracker.training.api;` indicates that this interface belongs to the
// `pl.wsb.fitnesstracker.training.api` package.
package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
