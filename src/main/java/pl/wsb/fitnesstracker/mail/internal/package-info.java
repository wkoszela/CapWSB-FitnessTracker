// The `@NonNullByDefault` annotation in Java is used to specify that all elements within a package are
// considered as non-null by default unless explicitly annotated with `@Nullable`. In this case, it is
// applied to the package `pl.wsb.fitnesstracker.mail.internal`, meaning that all elements (such as
// methods, parameters, and fields) within this package are expected to be non-null unless specified
// otherwise. This helps in enforcing non-nullability at the package level, reducing the chances of
// null pointer exceptions in the code.
@NonNullByDefault
package pl.wsb.fitnesstracker.mail.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;