// This Java code snippet defines an enumeration `ActivityType` representing different types of
// physical activities. Each activity type is defined with a corresponding display name. The activities
// included in this enumeration are RUNNING, CYCLING, WALKING, SWIMMING, and TENNIS.
package pl.wsb.fitnesstracker.training.internal;

/**
 * Enumeration representing different types of physical activities.
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
