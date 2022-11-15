module PublicTransport {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires javafx.graphics;
    requires log4j.api;
    requires java.sql;
    requires java.naming;

    opens com.example.Application;
    opens com.example.HibernateOracle.Model;
    opens com.example.JFX.Controller;

}