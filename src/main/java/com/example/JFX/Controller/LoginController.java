package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.HibernateOracle.Model.*;
import com.example.Service.Classes.AuthenticationService;
import com.example.Service.Classes.TravelService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Button buttonSignUp;
    @FXML
    public Label loginMessageLabel;
    @FXML
    public Button buttonReset;
    @FXML
    public Button buttonSignIn;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameTextField;
    @FXML
    public ImageView lockImageView;
    @FXML
    public Hyperlink forgotPasswordHyperlink;
    @FXML
    public Button buttonExit;

    private final AuthenticationService authenticationService = new AuthenticationService();
    private final TravelService travelService = new TravelService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeExpiredTrips();
        setTexts();
        exit();
    }
    @FXML
    public void buttonSignIn(ActionEvent actionEvent) {
        if(!validFields()){
            loginMessageLabel.setTextFill(Color.RED);
            loginMessageLabel.setText("Username and password can't be empty!");
            return;
        }

        if (!validateLogin()) {
            loginMessageLabel.setTextFill(Color.RED);
            loginMessageLabel.setText("User not found!");
            return;
        }

        loginMessageLabel.setTextFill(Color.GREEN);
        loginMessageLabel.setText("Welcome, " + CurrentUser.getUser().getFirstName() + " " +
                CurrentUser.getUser().getLastName());

        Log4j.logger.info("User named:" + CurrentUser.getUser().getUserName() + " is logged in");
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event2 -> {
            try {
                if(CurrentUser.getUser().getClass() == CustomerEntity.class){
                    SceneController.getCustomerHomeScene(actionEvent);
                    return;
                }
                if(CurrentUser.getUser().getClass() == AdminEntity.class){
                    SceneController.getAdminHomeScene(actionEvent);
                    return;
                }
                if(CurrentUser.getUser().getClass() == TravelCompanyEntity.class){
                    SceneController.getTravelCompanyHomeScene(actionEvent);
                    return;
                }
                if(CurrentUser.getUser().getClass() == CashierEntity.class){
                    SceneController.getCashierHomeScene(actionEvent);
                    return;
                }
                if(CurrentUser.getUser().getClass() == DistributorEntity.class){
                    SceneController.getDistributorScene(actionEvent);
                    return;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();


    }
    @FXML
    public void buttonReset(ActionEvent actionEvent) {
        usernameTextField.setText("");
        passwordField.setText("");
        loginMessageLabel.setText("");
    }
    @FXML
    public void buttonSignUp(ActionEvent actionEvent) throws IOException {
       SceneController.getRegisterScene(actionEvent);

    }
    @FXML
    public void hyperlinkForgotPassword(ActionEvent actionEvent) {
        //Forgot password !
    }

    private boolean validFields() {
        return !usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty();
    }

    private boolean validateLogin() {

        if (authenticationService.loginUser(usernameTextField.getText(),passwordField.getText()) == null){
            return false;
        }
        else{
            CurrentUser.setUser(authenticationService.loginUser(usernameTextField.getText(),passwordField.getText()));
            return true;
        }

    }

    private void exit() {
        buttonExit.setOnAction(SceneController::close);
    }

    private void setTexts(){
        File lockFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\KeyLockImage.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    private void removeExpiredTrips(){
        travelService.removeExpiredTrips();
    }

}