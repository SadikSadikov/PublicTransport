package com.example.Service.Classes;

import com.example.HibernateOracle.DAO.CustomerDAO;
import com.example.HibernateOracle.Model.CustomerEntity;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.List;

public class CustomerService{

    private final CustomerDAO customerDao = new CustomerDAO();


    public XYChart.Series getTop5CustomerMostPurchasedTickets(XYChart.Series series){

        for(int i = 0 ; i < customerDao.getNameTop5Customer().size() ; i++){
            series.getData().add(new XYChart.Data(customerDao.getNameTop5Customer().get(i),
                    customerDao.getNumberOfTicketsTop5Customer().get(i)));
        }
        return series;
    }


    public int getTotalTicketsWithID(int idTravel) {
        return customerDao.getTotalTickets(idTravel);
    }


    public boolean updateTotalTickets(int totalTickets, int idTravel) {
        return customerDao.updateTotalTickets(totalTickets,idTravel);
    }


    public List<CustomerEntity> getCustomerWithDate(LocalDate firstDate, LocalDate secondDate) {
        return customerDao.getCustomerWithDate(firstDate,secondDate);
    }

}
