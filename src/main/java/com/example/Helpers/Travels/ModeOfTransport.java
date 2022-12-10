package com.example.Helpers.Travels;

public enum ModeOfTransport {
    BUS("Bus"),
    TRAIN("Train");

    private final String mode;

    ModeOfTransport(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
