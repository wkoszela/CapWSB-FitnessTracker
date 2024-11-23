package com.capgemini.wsb.fitnesstracker.training.internal;


import lombok.Getter;

/**
 * The enum Activity type.
 */
@Getter
public enum ActivityType {

    /**
     * Running activity type.
     */
    RUNNING("Running"),
    /**
     * Cycling activity type.
     */
    CYCLING("Cycling"),
    /**
     * Walking activity type.
     */
    WALKING("Walking"),
    /**
     * Swimming activity type.
     */
    SWIMMING("Swimming"),
    /**
     * Tennis activity type.
     */
    TENNIS("Tenis");

    /**
     * -- GETTER --
     *  Gets display name.
     *
     */
    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

}
