package com.example.dealershoprest.models.entities.enums;

public enum TransmissionType {
    MANUAL("Manual"),
    AUTOMATIC("Automatic");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    TransmissionType(String displayValue) {
        this.displayValue = displayValue;
    }
}
