package com.example.HibernateOracle.Model;

import com.example.Helpers.CurrentTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cashier")
public class CashierEntity extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int cashier_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private LocalDate date_cashier;

    @OneToMany(targetEntity = PurchasedTicketsEntity.class,cascade = CascadeType.ALL,mappedBy = "id_cashier_PT" ,fetch = FetchType.EAGER)
    private List<PurchasedTicketsEntity> id_cashier_PT = new ArrayList<>();

    @OneToMany(targetEntity = TravelEntity.class,cascade = CascadeType.ALL,mappedBy = "id_cashier_T" ,fetch = FetchType.EAGER)
    private List<TravelEntity> id_cashier_T = new ArrayList<>();

    public CashierEntity(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.date_cashier = Date.valueOf(CurrentTime.getTime()).toLocalDate();
    }

    public CashierEntity() {
    }

    @Id
    @Column(name = "cashier_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(int cashier_id) {
        this.cashier_id = cashier_id;
    }
    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "username")
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_cashier_PT(List<PurchasedTicketsEntity> id_cashier_PT) {
        this.id_cashier_PT = id_cashier_PT;
    }

    public void setId_cashier_T(List<TravelEntity> id_cashier_T) {
        this.id_cashier_T = id_cashier_T;
    }

    @Column(name = "date_cashier")
    public LocalDate getDate_cashier() {
        return date_cashier;
    }

    public void setDate_cashier(LocalDate date_cashier) {
        this.date_cashier = date_cashier;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "cashier_id=" + cashier_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                "} ";
    }
}
