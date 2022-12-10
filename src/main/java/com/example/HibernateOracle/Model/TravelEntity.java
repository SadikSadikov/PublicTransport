package com.example.HibernateOracle.Model;

import com.example.Helpers.CurrentTime;
import com.example.Helpers.Travels.Status;
import com.example.Helpers.Travels.TravelConvert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Travel")
public class TravelEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id_travel;
    private String typeOfTravel;
    private String startingStation;
    private String terminalStation;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfArrival;
    private int numberOfPlaces;
    private String  modeOfTransport;
    private int ticketLimit;
    private LocalDate date_travel;

    private String status;
    private double priceTicket;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cashier_T")
    private int id_cashier_T;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tc")
    private int id_tc;

    @OneToMany(targetEntity = PurchasedTicketsEntity.class,cascade = CascadeType.ALL,mappedBy = "id_travel_PT" ,fetch = FetchType.EAGER)
    private List<PurchasedTicketsEntity> id_travel_PT = new ArrayList<>();

    @OneToMany(targetEntity = MessagesEntity.class,cascade = CascadeType.ALL,mappedBy = "id_travel_M" ,fetch = FetchType.EAGER)
    private List<MessagesEntity> id_travel_M = new ArrayList<>();




    public TravelEntity(String typeOfTravel, String startingStation,String terminalStation, LocalDate dateOfDeparture, LocalDate dateOfArrival, int numberOfPlaces, String modeOfTransport, int ticketLimit, TravelCompanyEntity id_tc,double priceTicket) {
        this.typeOfTravel = typeOfTravel;
        this.startingStation = startingStation;
        this.terminalStation = terminalStation;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.numberOfPlaces = numberOfPlaces;
        this.modeOfTransport = modeOfTransport;
        this.ticketLimit = ticketLimit;
        this.id_tc = TravelConvert.convertToEntityAttribute(id_tc);
        this.id_cashier_T = 1;
        this.status = Status.FALSE.getStatus();
        this.priceTicket = priceTicket;
        this.date_travel = Date.valueOf(CurrentTime.getTime()).toLocalDate();
    }


    public TravelEntity() {
    }
    @Id
    @Column(name = "id_travel")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getId_travel() {
        return id_travel;
    }

    public void setId_travel(int id_travel) {
        this.id_travel = id_travel;
    }
    @Column(name = "typeoftravel")
    public String getTypeOfTravel() {
        return typeOfTravel;
    }

    public void setTypeOfTravel(String typeOfTravel) {
        this.typeOfTravel = typeOfTravel;
    }

    @Column(name = "startingstation")
    public String getStartingStation() {
        return startingStation;
    }

    public void setStartingStation(String startingStation) {
        this.startingStation = startingStation;
    }


    @Column(name = "dateofdeperature")
    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    @Column(name = "dateofarrival")
    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    @Column(name = "numberofplaces")
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    @Column(name = "modeoftransport")
    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    @Column(name = "ticketlimit")
    public int getTicketLimit() {
        return ticketLimit;
    }

    public void setTicketLimit(int ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public int getId_cashier_T() {
        return id_cashier_T;
    }

    public void setId_cashier_T(int id_cashier_T) {
        this.id_cashier_T = id_cashier_T;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "priceticket")
    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public int getId_tc() {
        return id_tc;
    }

    public void setId_tc(int id_tc) {
        this.id_tc = id_tc;
    }

    public void setId_travel_PT(List<PurchasedTicketsEntity> id_travel_PT) {
        this.id_travel_PT = id_travel_PT;
    }

    @Column(name = "terminalstation")
    public String getTerminalStation() {
        return terminalStation;
    }

    public void setTerminalStation(String terminalStation) {
        this.terminalStation = terminalStation;
    }

    @Column(name = "date_travel")
    public LocalDate getDate_travel() {
        return date_travel;
    }

    public void setDate_travel(LocalDate date_travel) {
        this.date_travel = date_travel;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id_travel=" + id_travel +
                ", typeOfTravel='" + typeOfTravel + '\'' +
                ", startingStation='" + startingStation + '\'' +
                ", terminalStation='" + terminalStation + '\'' +
                ", dateOfDeparture='" + dateOfDeparture + '\'' +
                ", dateOfArrival='" + dateOfArrival + '\'' +
                ", numberOfPlaces=" + numberOfPlaces +
                ", modeOfTransport='" + modeOfTransport + '\'' +
                ", ticketLimit=" + ticketLimit +
                ", id_tc=" + id_tc +
                '}';
    }

}
