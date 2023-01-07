package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.TravelCompanyDAO;
import com.example.Service.Interfaces.ITravelCompanyService;

import java.util.List;


public class TravelCompanyService implements ITravelCompanyService {

    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();

    @Override
    public long getNumberOfTravelCompany() {
        return travelCompanyDAO.getNumberOfTravelCompany();
    }

    @Override
    public int getIdWithName(String nameTC) {
        return travelCompanyDAO.getIdWithName(nameTC);
    }

    @Override
    public List<String> getNameOfTravelCompany() {
        return travelCompanyDAO.getNamesOfTC();
    }


}
