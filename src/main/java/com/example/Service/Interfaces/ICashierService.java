package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.CashierEntity;
import com.example.HibernateOracle.Model.CustomerEntity;

import java.time.LocalDate;
import java.util.List;

public interface ICashierService {
    long getNumberOfCashier();
    List<CashierEntity> getCashierWithDate(LocalDate firstDate, LocalDate secondDate);
    List<String> getNameOfCashier();
    int getIdWithName(String nameCashier);
}
