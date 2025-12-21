// This code snippet is defining a Java record class named `UserEmailDto` in the
// `pl.wsb.fitnesstracker.user.api` package. The record has two components: `id` of type `Long` and
// `email` of type `String`. This record is used for returning minimal user information during an email
// search. Records are a new feature introduced in Java 14 for concise data classes. They automatically
// generate getters, `equals()`, `hashCode()`, and `toString()` methods based on the components defined
// in the record.
package pl.wsb.fitnesstracker.user.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    private UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
