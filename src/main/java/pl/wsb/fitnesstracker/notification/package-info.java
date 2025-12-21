// The `@NonNullByDefault` annotation in Java is used to specify that all elements within a package are
// considered as non-null by default unless explicitly annotated with `@Nullable`. This means that if a
// method parameter, return value, or field is not annotated with `@Nullable`, it is assumed to be
// non-null. This can help improve code readability and catch potential null pointer exceptions at
// compile time.
@NonNullByDefault
package pl.wsb.fitnesstracker.notification;

import org.eclipse.jdt.annotation.NonNullByDefault;