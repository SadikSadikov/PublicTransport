package com.example.Helpers;

public enum ScenePath {
    LOGIN("/com/example/JFX/login.fxml"),
    REGISTER("/com/example/JFX/register.fxml"),
    HOME_CUSTOMER("/com/example/JFX/homeCustomer.fxml"),
    HOME_ADMIN("/com/example/JFX/homeAdmin.fxml"),
    HOME_TRAVEL_COMPANY("/com/example/JFX/homeTC.fxml"),
    HOME_CASHIER("/com/example/JFX/homeCashier.fxml"),
    HOME_DISTRIBUTOR("/com/example/JFX/homeDistributor.fxml");


    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
