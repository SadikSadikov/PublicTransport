package com.example.HibernateOracle.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TravelCompany")
public class TravelCompanyEntity extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int tc_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    @OneToMany(targetEntity = TravelEntity.class,cascade = CascadeType.ALL,mappedBy = "id_tc" ,fetch = FetchType.EAGER)
    private List<TravelEntity> id_tc = new ArrayList<>();

    @OneToMany(targetEntity = MessagesEntity.class,cascade = CascadeType.ALL,mappedBy = "id_tc_M" ,fetch = FetchType.EAGER)
    private List<MessagesEntity> id_tc_M = new ArrayList<>();


    public TravelCompanyEntity(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;

    }


    public TravelCompanyEntity() {
    }

    @Id
    @Column(name = "tc_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getTc_id() {
        return tc_id;
    }

    public void setTc_id(int tc_id) {
        this.tc_id = tc_id;
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

    public void setId_tc(TravelEntity id_tc) {
        this.id_tc.add(id_tc);
    }

    @Override
    public String toString() {
        return "TravelCompany{" +
                "admin_id=" + tc_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                "} ";
    }

}
