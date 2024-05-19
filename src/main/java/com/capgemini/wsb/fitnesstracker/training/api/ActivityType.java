package com.capgemini.wsb.fitnesstracker.training.api;

/**
 * ActivityType is an enum class that represents the type of activity that can be performed during a training.
 * It is used to store the type of activity in the database.
 *
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
