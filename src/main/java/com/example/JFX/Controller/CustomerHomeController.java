package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.HibernateOracle.Model.AdminEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerHomeController implements Initializable {
    @FXML
    public Label welcomeLabel;
    @FXML
    public Button buttonLogout;
    @FXML
    public ImageView logoutImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTexts();


    }

    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        SceneController.getLoginScene(actionEvent);
    }

    private void setTexts(){
        File logoutFile = new File("Images/Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);
        welcomeLabel.setText("Welcome, "+ CurrentUser.getUser().getUserName());



    }
}
