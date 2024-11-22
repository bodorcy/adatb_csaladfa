package com.csaladfa.model;

public enum EventType {
    BIRTH("Birth"),
    DEATH("Death"),
    MARRIAGE("Marriage"),
    DIVORCE("Divorce");

    private final String displayName;

    // Constructor
    EventType(String displayName) {
        this.displayName = displayName;
    }

    // Getter for display name
    public String getDisplayName() {
        return displayName;
    }

    // Optionally, you can override toString() if you want to return the display name in toString() calls.
    @Override
    public String toString() {
        return displayName;
    }

    // Static method to get an enum from a string value (useful for persistence or incoming data)
    public static EventType fromString(String text) {
        for (EventType eventType : EventType.values()) {
            if (eventType.displayName.equalsIgnoreCase(text)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + text);
    }
}
