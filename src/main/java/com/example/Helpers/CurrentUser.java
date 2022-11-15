package com.example.Helpers;

import com.example.HibernateOracle.Model.User;

public class CurrentUser {
    private static User user;

    private CurrentUser(){}

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentUser.user = user;
    }
}
