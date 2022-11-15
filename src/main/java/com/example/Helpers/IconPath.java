package com.example.Helpers;

public enum IconPath {
    MAIN("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\BusIcon.jpg");


    private final String path;

    private IconPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
