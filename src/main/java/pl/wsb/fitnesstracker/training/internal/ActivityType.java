package pl.wsb.fitnesstracker.training.internal;

/**
 * Enumeration reprezentująca różne typy aktywności fizycznych.
 * <p>
 * Definiuje dostępne rodzaje treningów, które mogą być wykonywane przez użytkowników.
 * Każdy typ aktywności ma przypisaną anglojęzyczną nazwę wyświetlową (displayName).
 * </p>
 * <p>
 * Dostępne typy:
 * - RUNNING: Bieganie
 * - CYCLING: Jazda na rowerze
 * - WALKING: Chodzenie
 * - SWIMMING: Pływanie
 * - TENNIS: Tenis
 * </p>
 *
 * @author Fitness Tracker Team
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

    public String getDisplayName() {
        return displayName;
    }

}
