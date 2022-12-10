package com.example.HibernateOracle.Model;

import com.example.Helpers.CurrentTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "Distributor")
public class DistributorEntity extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int distributor_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private LocalDate date_distributor;

    public DistributorEntity(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.date_distributor = Date.valueOf(CurrentTime.getTime()).toLocalDate();
    }

    public DistributorEntity() {
    }
    @Id
    @Column(name = "distributor_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(int distributor_id) {
        this.distributor_id = distributor_id;
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

    @Column(name = "date_distributor")
    public LocalDate getDate_distributor() {
        return date_distributor;
    }

    public void setDate_distributor(LocalDate date_distributor) {
        this.date_distributor = date_distributor;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "distributor_id=" + distributor_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                "} ";
    }
}
