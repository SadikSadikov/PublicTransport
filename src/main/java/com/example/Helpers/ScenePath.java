package com.example.Helpers;

public enum ScenePath {
    LOGIN("/com/example/JFX/login.fxml"),
    REGISTER("/com/example/JFX/register.fxml"),
    HOME_CUSTOMER("/com/example/JFX/homeCustomer.fxml"),
    HOME_ADMIN("/com/example/JFX/homeAdmin.fxml");

    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
