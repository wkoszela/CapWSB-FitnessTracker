package pl.wsb.fitnesstracker.training.internal;

import lombok.Getter;

/**
 * Denotes a type of activity such as sports
 */
@Getter
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
}
