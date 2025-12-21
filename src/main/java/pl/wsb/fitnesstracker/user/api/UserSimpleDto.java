// This code snippet is defining a Java record class named `UserSimpleDto` in the
// `pl.wsb.fitnesstracker.user.api` package. The record is used for returning simplified user
// information and contains three components: `id` of type `Long`, `firstName` of type `String`, and
// `lastName` of type `String`. Records in Java are a concise way to create classes that are mainly
// used for holding data. In this case, the `UserSimpleDto` record is used to represent simplified user
// information with just the id, first name, and last name.
package pl.wsb.fitnesstracker.user.api;

/**
 * Record used for returning simplified user information (id and name only).
 */
public record UserSimpleDto(Long id, String firstName, String lastName) {
}