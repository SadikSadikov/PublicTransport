package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.PurchasedTicketsEntity;

import java.time.LocalDate;
import java.util.List;

public interface IPurchasedTicketsService {
    boolean addPurchasedTickets(int idTravel, int idCashier, int idCustomer, int purchasedTickets);
    List<Integer> getTotalPurchasedTickets();
    List<PurchasedTicketsEntity> getPurchasedTicketsWithDate(LocalDate firstDate, LocalDate secondDate);
}
