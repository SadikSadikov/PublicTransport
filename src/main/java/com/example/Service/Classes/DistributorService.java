package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.DistributorDAO;
import com.example.HibernateOracle.Model.DistributorEntity;

import java.time.LocalDate;
import java.util.List;

public class DistributorService{

    private final DistributorDAO distributorDAO = new DistributorDAO();


    public long getNumberOfDistributor() {
        return distributorDAO.getNumberOfDistributor();
    }


    public List<DistributorEntity> getDistributorWithDate(LocalDate firstDate, LocalDate secondDate) {
        return distributorDAO.getDistributorWithDate(firstDate, secondDate);
    }
}
