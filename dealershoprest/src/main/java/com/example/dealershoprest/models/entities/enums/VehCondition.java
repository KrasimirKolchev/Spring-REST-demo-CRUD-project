package com.example.dealershoprest.models.entities.enums;

public enum VehCondition {
    NEW("New"),
    USED("Used");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    VehCondition(String displayValue) {
        this.displayValue = displayValue;
    }
}
