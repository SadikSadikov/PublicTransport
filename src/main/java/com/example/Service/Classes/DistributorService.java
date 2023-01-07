package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.DistributorDAO;
import com.example.HibernateOracle.Model.DistributorEntity;
import com.example.Service.Interfaces.IDistributorService;

import java.time.LocalDate;
import java.util.List;

public class DistributorService implements IDistributorService {

    private final DistributorDAO distributorDAO = new DistributorDAO();

    @Override
    public long getNumberOfDistributor() {
        return distributorDAO.getNumberOfDistributor();
    }

    @Override
    public List<DistributorEntity> getDistributorWithDate(LocalDate firstDate, LocalDate secondDate) {
        return distributorDAO.getDistributorWithDate(firstDate, secondDate);
    }
}
