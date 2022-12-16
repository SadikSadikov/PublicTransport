package com.example.HibernateOracle.Model;

import com.example.Helpers.CurrentTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "PurchasedTickets")
public class PurchasedTicketsEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id_purchasedTickets;
    private int id_travel_PT;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cashier_PT")
    private int id_cashier_PT;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer_PT")
    private int id_customer_PT;

    private int numberOfTickets;

    private LocalDate date_purchasedTickets;



    public PurchasedTicketsEntity(int id_travel_PT, int id_cashier_PT, int id_customer_PT, int numberOfTickets) {
        this.id_travel_PT = id_travel_PT;
        this.id_cashier_PT = id_cashier_PT;
        this.id_customer_PT = id_customer_PT;
        this.numberOfTickets = numberOfTickets;
        this.date_purchasedTickets = Date.valueOf(CurrentTime.getTime()).toLocalDate();
    }

    public PurchasedTicketsEntity() {
    }

    @Id
    @Column(name = "id_purchasedtickets")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getId_purchasedTickets() {
        return id_purchasedTickets;
    }

    public void setId_purchasedTickets(int id_purchasedTickets) {
        this.id_purchasedTickets = id_purchasedTickets;
    }

    @Column(name = "id_travel_pt")
    public int getId_travel_PT() {
        return id_travel_PT;
    }

    public void setId_travel_PT(int id_travel_PT) {
        this.id_travel_PT = id_travel_PT;
    }

    public int getId_cashier_PT() {
        return id_cashier_PT;
    }

    public void setId_cashier_PT(int id_cashier_PT) {
        this.id_cashier_PT = id_cashier_PT;
    }

    public int getId_customer_PT() {
        return id_customer_PT;
    }

    public void setId_customer_PT(int id_customer_PT) {
        this.id_customer_PT = id_customer_PT;
    }

    @Column(name = "numberoftickets")
    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Column(name = "date_purchasedtickets")
    public LocalDate getDate_purchasedTickets() {
        return date_purchasedTickets;
    }

    public void setDate_purchasedTickets(LocalDate date_purchasedTickets) {
        this.date_purchasedTickets = date_purchasedTickets;
    }
}
