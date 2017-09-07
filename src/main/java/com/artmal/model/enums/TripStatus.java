package com.artmal.model.enums;

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
