package pl.wsb.fitnesstracker.training.internal;

/**
 * Wyliczenie reprezentujące różne rodzaje aktywności fizycznej.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Pobiera nazwę wyświetlaną aktywności.
     *
     * @return nazwa wyświetlana
     */
    public String getDisplayName() {
        return displayName;
    }

}
