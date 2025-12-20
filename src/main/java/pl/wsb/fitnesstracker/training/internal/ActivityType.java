package pl.wsb.fitnesstracker.training.internal;

/**
 * Enumerates the activity types that can be stored in a {@link pl.wsb.fitnesstracker.training.api.Training}
 * record.  Each constant carries a human‑readable display name that can be shown in
 * the UI or exported to CSV/JSON.
 *
 * <p>Typical usage:</p>
 * <pre>{@code
 * Training training = new Training(
 *         user,
 *         LocalDateTime.now(),
 *         LocalDateTime.now().plusHours(1),
 *         ActivityType.RUNNING,
 *         10.0,    // distance in kilometres
 *         8.5);    // average speed km/h
 * }</pre>
 *
 * <h2>Constants</h2>
 * <ul>
 *   <li>{@code RUNNING} – “Running”</li>
 *   <li>{@code CYCLING} – “Cycling”</li>
 *   <li>{@code WALKING} – “Walking”</li>
 *   <li>{@code SWIMMING} – “Swimming”</li>
 *   <li>{@code TENNIS}  – “Tenis” (note the typo; it is kept for backward‑compatibility)</li>
 * </ul>
 *
 * <h2>Design notes</h2>
 * <ul>
 *   <li>The enum stores the display name in a {@code final String} field so that
 *       it can be accessed without allocating a new string on every call.</li>
 *   <li>It is intentionally not annotated with {@code @JsonValue} because the
 *       API prefers the numeric ordinal when serialising to JSON.  If a string
 *       representation is required, use {@link #getDisplayName()} or override
 *       {@code toString()}.  For example:</li>
 * </ul>
 *
 * <pre>{@code
 * @JsonValue
 * public String toString() {
 *     return displayName;
 * }
 * }</pre>
 *
 * <h2>Future improvements</h2>
 * <ul>
 *   <li>Fix the typo in {@code TENNIS} (currently “Tenis”).  If you change the
 *       constant name, remember to update any database seed data or tests that
 *       rely on the old value.</li>
 *   <li>Add a {@code fromDisplayName(String)} helper that returns the matching
 *       enum value or throws an informative exception.</li>
 * </ul>
 *
 * @see pl.wsb.fitnesstracker.training.api.Training
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");   // keep the legacy spelling for compatibility

    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the human‑readable name that should be shown to users.
     *
     * @return a capitalised activity name, e.g. {@code "Running"}
     */
    public String getDisplayName() {
        return displayName;
    }
}
