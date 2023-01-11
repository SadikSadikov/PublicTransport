package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.CashierDAO;
import com.example.HibernateOracle.Model.CashierEntity;

import java.time.LocalDate;
import java.util.List;

public class CashierService{

    private final CashierDAO cashierDAO = new CashierDAO();


    public long getNumberOfCashier() {
        return cashierDAO.getNumberOfCashier();
    }


    public List<CashierEntity> getCashierWithDate(LocalDate firstDate, LocalDate secondDate) {
        return cashierDAO.getCashierWithDate(firstDate, secondDate);
    }


    public List<String> getNameOfCashier() {
        return cashierDAO.getNamesOfCashier();
    }


    public int getIdWithName(String nameCashier) {
        return cashierDAO.getIdWithName(nameCashier);
    }
}
