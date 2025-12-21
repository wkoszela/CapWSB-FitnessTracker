// This code snippet is defining a Java record class named `UserEmailDto` in the
// `pl.wsb.fitnesstracker.user.api` package. The record has two components: `id` of type `Long` and
// `email` of type `String`. This record is used for returning minimal user information during an email
// search. Records are a new feature introduced in Java 14 for concise data classes. They provide a
// compact way to define classes that are mainly used for holding data.
package pl.wsb.fitnesstracker.user.api;

/**
 * Record used for returning minimal user information during email search.
 */
public record UserEmailDto(Long id, String email) {
}