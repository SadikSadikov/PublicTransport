package com.example.Helpers;

public enum NotificationsPath {
    DISTRIBUTOR_NEW_TRAVEL_FILE("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Files\\DistributorNewTravelFile.txt"),
    DISTRIBUTOR_CANCEL_TRAVEL_FILE("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Files\\DistributorCancelTravelFile.txt");

    private final String path;

    NotificationsPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
