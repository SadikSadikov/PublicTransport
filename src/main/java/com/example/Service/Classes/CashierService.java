package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.CashierDAO;
import com.example.HibernateOracle.Model.CashierEntity;
import com.example.Service.Interfaces.ICashierService;
import com.example.Service.Interfaces.ICreateUserService;

import java.time.LocalDate;
import java.util.List;

public class CashierService implements ICashierService {

    private final CashierDAO cashierDAO = new CashierDAO();

    @Override
    public long getNumberOfCashier() {
        return cashierDAO.getNumberOfCashier();
    }

    @Override
    public List<CashierEntity> getCashierWithDate(LocalDate firstDate, LocalDate secondDate) {
        return cashierDAO.getCashierWithDate(firstDate, secondDate);
    }

    @Override
    public List<String> getNameOfCashier() {
        return cashierDAO.getNamesOfCashier();
    }

    @Override
    public int getIdWithName(String nameCashier) {
        return cashierDAO.getIdWithName(nameCashier);
    }
}
