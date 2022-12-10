package com.example.HibernateOracle.Model;

import com.example.Helpers.CurrentTime;
import javafx.scene.chart.PieChart;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class CustomerEntity extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int customer_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private LocalDate date_customer;
    private int total_tickets;

    @OneToMany(targetEntity = PurchasedTicketsEntity.class,cascade = CascadeType.ALL,mappedBy = "id_customer_PT" ,fetch = FetchType.EAGER)
    private List<PurchasedTicketsEntity> id_customer_PT = new ArrayList<>();

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, String userName, String password) {
        super(firstName,lastName,userName,password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        date_customer = Date.valueOf(CurrentTime.getTime()).toLocalDate();
        this.total_tickets = 0;
    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public void setId_customer_PT(List<PurchasedTicketsEntity> id_customer_PT) {
        this.id_customer_PT = id_customer_PT;
    }

    @Column(name = "date_customer")
    public LocalDate getDate_customer() {
        return date_customer;
    }

    public void setDate_customer(LocalDate date_customer) {
        this.date_customer = date_customer;
    }

    @Column(name = "total_tickets")
    public int getTotal_tickets() {
        return total_tickets;
    }

    public void setTotal_tickets(int total_tickets) {
        this.total_tickets = total_tickets;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
