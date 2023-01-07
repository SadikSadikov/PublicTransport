package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.User;

public interface IAuthenticationService {
    User loginUser(String username, String password);

}
