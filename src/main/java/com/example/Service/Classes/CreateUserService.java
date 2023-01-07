package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.*;
import com.example.HibernateOracle.Model.CashierEntity;
import com.example.HibernateOracle.Model.CustomerEntity;
import com.example.HibernateOracle.Model.DistributorEntity;
import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.Service.Interfaces.ICreateUserService;

public class CreateUserService implements ICreateUserService {
    private final CustomerDAO customerDao = new CustomerDAO();
    private final AdminDAO adminDao = new AdminDAO();
    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();
    private final CashierDAO cashierDAO = new CashierDAO();
    private final DistributorDAO distributorDAO = new DistributorDAO();

    @Override
    public boolean createUser(String typeUser, String firstName, String lastName, String userName, String password) {
        if (typeUser.equals("Customer")){
            return customerDao.addData(new CustomerEntity(firstName, lastName ,userName, password));
        }
        if (typeUser.equals("Distributor")){
            return  distributorDAO.addData(new DistributorEntity(firstName, lastName ,userName, password));
        }
        if (typeUser.equals("Cashier")){
            return cashierDAO.addData(new CashierEntity(firstName, lastName, userName, password));
        }
        if (typeUser.equals("TravelCompany")){
            return (travelCompanyDAO.addData(new TravelCompanyEntity(firstName, lastName, userName, password)));
        }
        return false;
    }
}
