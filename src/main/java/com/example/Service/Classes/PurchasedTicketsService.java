package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.PurchasedTicketsDAO;
import com.example.HibernateOracle.Model.PurchasedTicketsEntity;
import com.example.Service.Interfaces.IPurchasedTicketsService;

import java.time.LocalDate;
import java.util.List;

public class PurchasedTicketsService implements IPurchasedTicketsService {

    private final PurchasedTicketsDAO purchasedTicketsDAO = new PurchasedTicketsDAO();

    @Override
    public boolean addPurchasedTickets(int idTravel, int idCashier, int idCustomer, int purchasedTickets) {
        return purchasedTicketsDAO.addData(new PurchasedTicketsEntity(idTravel,idCashier,idCustomer,purchasedTickets));
    }

    @Override
    public List<Integer> getTotalPurchasedTickets() {
        return purchasedTicketsDAO.getTotalPurchasedTickets();
    }

    @Override
    public List<PurchasedTicketsEntity> getPurchasedTicketsWithDate(LocalDate firstDate, LocalDate secondDate) {
        return purchasedTicketsDAO.getPurchasedTicketsWithDate(firstDate, secondDate);
    }
}
