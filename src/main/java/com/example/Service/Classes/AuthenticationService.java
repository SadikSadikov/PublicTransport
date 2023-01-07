package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.*;
import com.example.HibernateOracle.Model.*;
import com.example.Service.Interfaces.IAuthenticationService;

public class AuthenticationService implements IAuthenticationService {

    private final CustomerDAO customerDao = new CustomerDAO();
    private final AdminDAO adminDao = new AdminDAO();
    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();
    private final CashierDAO cashierDAO = new CashierDAO();
    private final DistributorDAO distributorDAO = new DistributorDAO();

    @Override
    public User loginUser(String username, String password) {
        CustomerEntity customer = customerDao.getConnectedUser(username, password);
        AdminEntity admin = adminDao.getConnectedAdmin(username, password);
        TravelCompanyEntity travelCompany = travelCompanyDAO.getConnectedUser(username, password);
        CashierEntity cashier = cashierDAO.getConnectedUser(username, password);
        DistributorEntity distributor = distributorDAO.getConnectedUser(username, password);

        if (customer != null) {
            return customer;
        }
        if(admin != null){
            return admin;
        }
        if(travelCompany != null){
            return travelCompany;
        }
        if(cashier != null){
            return cashier;
        }
        if(distributor != null){
            return distributor;
        }

        return null;
    }


}
