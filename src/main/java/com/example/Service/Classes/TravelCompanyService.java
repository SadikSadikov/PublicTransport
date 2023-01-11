package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.TravelCompanyDAO;

import java.util.List;


public class TravelCompanyService{

    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();


    public long getNumberOfTravelCompany() {
        return travelCompanyDAO.getNumberOfTravelCompany();
    }


    public int getIdWithName(String nameTC) {
        return travelCompanyDAO.getIdWithName(nameTC);
    }


    public List<String> getNameOfTravelCompany() {
        return travelCompanyDAO.getNamesOfTC();
    }


}
