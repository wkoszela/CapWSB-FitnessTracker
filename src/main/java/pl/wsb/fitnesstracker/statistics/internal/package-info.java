// The `@NonNullByDefault` annotation in Java is used to specify that all variables and parameters
// within a package are considered as non-null by default unless explicitly annotated with `@Nullable`.
// In this case, it is being applied at the package level for the
// `pl.wsb.fitnesstracker.statistics.internal` package, meaning that all elements within this package
// are assumed to be non-null unless specified otherwise. This helps in improving code readability and
// reducing the chances of null pointer exceptions.
@NonNullByDefault
package pl.wsb.fitnesstracker.statistics.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;