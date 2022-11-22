package com.example.HibernateOracle.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Admin")
public class AdminEntity extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int admin_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public AdminEntity(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;

    }

    public AdminEntity() {
    }

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                "} ";
    }
}
