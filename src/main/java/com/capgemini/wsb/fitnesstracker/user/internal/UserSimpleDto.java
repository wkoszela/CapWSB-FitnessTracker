
package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**

 * @param Id        opcjonalne unikalne ID użytkownika, może być null
 * @param firstName imię użytkownika
 * @param lastName  nazwisko użytkownika
 */
record UserSimpleDto(@Nullable Long Id, String firstName, String lastName) {
}
