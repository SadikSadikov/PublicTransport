package com.example.HibernateOracle.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Messages")
public class MessagesEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id_message;
    private String request;
    private String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_travel_M")
    private int id_travel_M;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tc_M")
    private int id_tc_M;

    public MessagesEntity(String request, String answer, int id_travel_M,int id_tc_M) {
        this.request = request;
        this.answer = answer;
        this.id_travel_M = id_travel_M;
        this.id_tc_M = id_tc_M;
    }

    public MessagesEntity() {
    }

    @Id
    @Column(name = "id_message")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementor",strategy = "increment")
    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    @Column(name = "request")
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId_travel_M() {
        return id_travel_M;
    }

    public void setId_travel_M(int id_travel_M) {
        this.id_travel_M = id_travel_M;
    }

    public int getId_tc_M() {
        return id_tc_M;
    }

    public void setId_tc_M(int id_tc_M) {
        this.id_tc_M = id_tc_M;
    }
}
