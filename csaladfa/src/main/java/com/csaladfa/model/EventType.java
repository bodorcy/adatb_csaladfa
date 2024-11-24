package com.csaladfa.model;

public enum EventType {
    BIRTH("Birth"),
    DEATH("Death"),
    MARRIAGE("Marriage"),
    DIVORCE("Divorce");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static EventType fromString(String text) {
        for (EventType eventType : EventType.values()) {
            if (eventType.displayName.equalsIgnoreCase(text)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + text);
    }
}
