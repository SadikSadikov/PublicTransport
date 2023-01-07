package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.CustomerEntity;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerService {
    XYChart.Series getTop5CustomerMostPurchasedTickets(XYChart.Series series);
    int getTotalTicketsWithID(int idTravel);
    boolean updateTotalTickets(int totalTickets, int idTravel);
    List<CustomerEntity> getCustomerWithDate(LocalDate firstDate, LocalDate secondDate);
}
