package com.example.Helpers.Travels;

public enum Destination {
    VARNA("Varna"),
    SOFIA("Sofia"),
    SHUMEN("Shumen"),
    DOBRICH("Dobrich");

    private final String destination;

    Destination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
