package com.splitwise.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExpenseCategory {
    FOOD("FOOD"),
    TRANSPORTATION("TRANSPORTATION"),
    HOUSING("HOUSING"),
    RENT("RENT"),
    ENTERTAINMENT("ENTERTAINMENT"),
    HEALTH("HEALTH"),
    EDUCATION("EDUCATION"),
    UTILITIES("UTILITIES"),
    BILL("BILL"),
    TRAVEL("TRAVEL"),
    OTHER("OTHER");


    private final String value;

    ExpenseCategory(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ExpenseCategory fromValue(String value) {
        for (ExpenseCategory type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null; // Handle unknown values as needed
    }
}
