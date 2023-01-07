package com.example.Service.Classes;

import com.example.Helpers.CurrentTime;
import com.example.HibernateOracle.DAO.TravelDAO;
import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Model.TravelEntity;
import com.example.Service.Interfaces.ITravelService;

import java.time.LocalDate;
import java.util.List;

public class TravelService implements ITravelService {
    private final TravelDAO travelDAO = new TravelDAO();

    @Override
    public boolean addTravel(String typeOfTravel, String startingStation, String terminalStation, LocalDate dateOfDeparture, LocalDate dateOfArrival, int numberOfPlaces, String modeOfTransport, int ticketLimit, TravelCompanyEntity idTC, double priceTicket) {
        return travelDAO.addData(new TravelEntity(typeOfTravel,startingStation,terminalStation,dateOfDeparture,dateOfArrival,numberOfPlaces,modeOfTransport,ticketLimit,idTC,priceTicket));
    }

    @Override
    public void removeExpiredTrips() {
        for(Integer i : travelDAO.removeExpiredTrips(CurrentTime.getTime())){
            travelDAO.deleteData(i);
        }
    }

    @Override
    public List<LocalDate> getTravelsWithUnsoldTickets() {
        return travelDAO.getTravelsWithUnsoldTickets();
    }

    @Override
    public List<TravelEntity> searchTicketsToBuy(String typeOfTravel, LocalDate dateOfDeparture, LocalDate dateOfArrival, String startingStation, String terminalStation, String modeOfTransport, String status) {
        return travelDAO.searchTickets(typeOfTravel, dateOfDeparture, dateOfArrival, startingStation, terminalStation, modeOfTransport, status);
    }

    @Override
    public int getTotalTicketsWithID(int idTravel) {
        return travelDAO.getTotalTickets(idTravel);
    }

    @Override
    public boolean updateTicketNumber(int idTravel, int remainingTickets) {
        return travelDAO.updateTicketNumber(idTravel, remainingTickets);
    }

    @Override
    public double getPriceTicketWithID(int idTravel) {
        return travelDAO.getPriceTicket(idTravel);
    }

    @Override
    public int getIdCashierWithID(int idCashier) {
        return travelDAO.getIdCashier(idCashier);
    }

    @Override
    public boolean updateStatus(String status, int id) {
        return travelDAO.updateStatus(status,id);
    }

    @Override
    public List<TravelEntity> getTravelsForCashier(int idCashier, String status) {
        return travelDAO.getTravelsForCashier(idCashier, status);
    }

    @Override
    public List<TravelEntity> getTravelsWithDate(LocalDate firstDate, LocalDate secondDate) {
        return travelDAO.getTravelsWithDate(firstDate, secondDate);
    }

    @Override
    public List<TravelEntity> getTravels() {
        return travelDAO.getTravels();
    }

    @Override
    public List<TravelEntity> getTravelsWithDateAndTC(int idTC, LocalDate firstDate, LocalDate secondDate) {
        return travelDAO.getTravelsWithDateAndTC(idTC, firstDate, secondDate);
    }

    @Override
    public int addIDCashier(int idTravel, int idCashier) {
        return travelDAO.addIdCashier(idTravel,idCashier);
    }

    @Override
    public boolean deleteTravel(int idTravel) {
        return travelDAO.deleteData(idTravel);
    }

    @Override
    public List<TravelEntity> getTravelForTC(int idTC) {
        return travelDAO.getTravel(idTC);
    }
}
