package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.CustomerEntity;
import com.example.HibernateOracle.Model.TravelCompanyEntity;

import java.time.LocalDate;
import java.util.List;

public interface ITravelCompanyService {
    long getNumberOfTravelCompany();
    int getIdWithName(String nameTC);
    List<String> getNameOfTravelCompany();

}
