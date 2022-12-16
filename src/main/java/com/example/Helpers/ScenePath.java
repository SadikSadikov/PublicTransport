package com.example.Helpers;

public enum ScenePath {
    LOGIN("/com/example/JFX/login.fxml"),
    REGISTER("/com/example/JFX/register.fxml"),
    HOME_CUSTOMER("/com/example/JFX/homeCustomer.fxml"),
    HOME_ADMIN("/com/example/JFX/homeAdmin.fxml"),
    HOME_TRAVEL_COMPANY("/com/example/JFX/homeTC.fxml"),
    HOME_CASHIER("/com/example/JFX/homeCashier.fxml"),
    HOME_DISTRIBUTOR("/com/example/JFX/homeDistributor.fxml"),
    BUYING_TICKETS("/com/example/JFX/fromForBuyingTicket.fxml"),
    USERS("/com/example/JFX/reference.fxml");


    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
