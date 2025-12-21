// The `@NonNullByDefault` annotation in Java is used to specify that all variables and parameters in a
// package are considered as non-null by default unless explicitly marked as nullable. In the provided
// code snippet, it is applied at the package level for the `pl.wsb.fitnesstracker.achievement`
// package, meaning that all elements within this package are assumed to be non-null unless specified
// otherwise. This can help improve code readability and reduce the chances of null pointer exceptions
// by enforcing non-null checks at compile time.
@NonNullByDefault
package pl.wsb.fitnesstracker.achievement;

// The `import org.eclipse.jdt.annotation.NonNullByDefault;` statement in Java is used to specify that
// all types in the package or compilation unit are considered as non-null by default. This means that
// unless explicitly annotated with `@Nullable`, variables, parameters, and return types are assumed to
// be non-null. This can help improve code readability and reduce the likelihood of null pointer
// exceptions by enforcing non-null checks at compile time.
import org.eclipse.jdt.annotation.NonNullByDefault;