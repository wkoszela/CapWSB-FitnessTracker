package com.capgemini.wsb.fitnesstracker.training.internal;

// TODO : JavaDoc
public enum ActivityType {

    RUNNING("Running", 60),
    CYCLING("Cycling", 23),
    WALKING("Walking", 100),
    SWIMMING("Swimming", 500),
    TENNIS("Tennis", 500);

    private final String displayName;
    private final double caloriesPerUnitOfDistance;

    ActivityType(String displayName, double caloriesPerUnitOfDistance) {
        this.displayName = displayName;
        this.caloriesPerUnitOfDistance = caloriesPerUnitOfDistance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getCaloriesPerUnitOfDistance() {
        return caloriesPerUnitOfDistance;
    }

}
