package com.example.JFX.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TCHomeController implements Initializable {
    @FXML
    public ImageView logoutImageView;
    @FXML
    public Button buttonLogout;

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
    }
}
