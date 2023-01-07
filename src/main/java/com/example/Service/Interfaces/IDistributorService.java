package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.DistributorEntity;

import java.time.LocalDate;
import java.util.List;

public interface IDistributorService {
    long getNumberOfDistributor();
    List<DistributorEntity> getDistributorWithDate(LocalDate firstDate, LocalDate secondDate);
}
