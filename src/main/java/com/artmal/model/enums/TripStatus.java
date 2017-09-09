package com.artmal.model.enums;

/**
 * Trips can have these statuses(only one in time).
 * @author Artem Malchenko
 */
public enum TripStatus {
    Open("Open"),
    In_progress("In progress"),
    Closed("Closed"),
    Canceled("Canceled");

    private String displayName;

    TripStatus(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }
}
