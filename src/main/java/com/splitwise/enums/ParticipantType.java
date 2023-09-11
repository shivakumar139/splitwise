package com.splitwise.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ParticipantType {
    USERS("USERS"),
    GROUPS("GROUPS");


    private final String value;

    ParticipantType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ParticipantType fromValue(String value) {
        for (ParticipantType type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null; // Handle unknown values as needed
    }
}
