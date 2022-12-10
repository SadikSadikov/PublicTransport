package com.example.Helpers.Travels;

public enum TypeOfTravel {
    ONE_WAY("One way"),
    TWO_WAY("Two way");

    private final String type;

    TypeOfTravel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
