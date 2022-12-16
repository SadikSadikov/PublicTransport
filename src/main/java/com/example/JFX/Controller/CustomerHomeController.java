package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
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
    @FXML
    public ImageView customerImageView;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView refreshImageView;
    @FXML
    public Button dashboardButton;
    @FXML
    public ImageView dashboardImageView;
    @FXML
    public Button buttonExit;
    @FXML
    public Label titleLabel;
    @FXML
    public ImageView busIconImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTexts();
        exit();
    }

    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
        SceneController.getCustomerHomeScene(actionEvent);
    }

    @FXML
    public void dashboardButton(ActionEvent actionEvent) throws IOException {
        SceneController.getBuyingTicketsScene(actionEvent);
    }

    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        Log4j.logger.warn("User named:" + CurrentUser.getUser().getUserName() + " has logged out ");
        SceneController.getLoginScene(actionEvent);
    }

    private void setTexts(){
        welcomeLabel.setText("Welcome, "+ CurrentUser.getUser().getUserName());

        File logoutFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);

        File userFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\user.png");
        Image userImage = new Image(userFile.toURI().toString());
        customerImageView.setImage(userImage);

        File refreshFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

        File busFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\bus.png");
        Image busImage = new Image(busFile.toURI().toString());
        busIconImageView.setImage(busImage);

        File dashboardFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\dashboard.png");
        Image dashboardImage = new Image(dashboardFile.toURI().toString());
        dashboardImageView.setImage(dashboardImage);

    }

    private void exit() {
        buttonExit.setOnAction(SceneController::close);
    }


}
