package com.example.Service.Interfaces;

import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Model.TravelEntity;

import java.time.LocalDate;
import java.util.List;

public interface ITravelService {
    boolean addTravel(String typeOfTravel, String startingStation, String terminalStation, LocalDate dateOfDeparture,
                      LocalDate dateOfArrival, int numberOfPlaces, String modeOfTransport, int ticketLimit,
                      TravelCompanyEntity idTC, double priceTicket);
    void removeExpiredTrips();
    List<LocalDate> getTravelsWithUnsoldTickets();
    List<TravelEntity> searchTicketsToBuy(String typeOfTravel,LocalDate dateOfDeparture,LocalDate dateOfArrival,
                                          String startingStation,String terminalStation,String modeOfTransport,String status);
    int getTotalTicketsWithID(int idTravel);
    boolean updateTicketNumber(int idTravel,int remainingTickets);
    double getPriceTicketWithID(int idTravel);
    int getIdCashierWithID(int idCashier);
    boolean updateStatus(String status, int id);
    List<TravelEntity> getTravelsForCashier(int idCashier, String status);
    List<TravelEntity> getTravelsWithDate(LocalDate firstDate, LocalDate secondDate);
    List<TravelEntity> getTravels();
    List<TravelEntity> getTravelsWithDateAndTC(int idTC, LocalDate firstDate, LocalDate secondDate);
    int addIDCashier(int idTravel, int idCashier);
    boolean deleteTravel(int idTravel);
    List<TravelEntity> getTravelForTC(int idTC);




}
