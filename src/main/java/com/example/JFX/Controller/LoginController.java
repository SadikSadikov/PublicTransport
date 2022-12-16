package com.example.JFX.Controller;

import com.example.Helpers.CurrentTime;
import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.HibernateOracle.DAO.*;
import com.example.HibernateOracle.Model.*;
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
import java.nio.file.DirectoryStream;
import java.util.Currency;
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

    private final CustomerDAO customerDao = new CustomerDAO();
    private final AdminDAO adminDao = new AdminDAO();
    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();
    private final CashierDAO cashierDAO = new CashierDAO();
    private final DistributorDAO distributorDAO = new DistributorDAO();
    private final TravelDAO travelDAO = new TravelDAO();

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
        //Forget password !
    }

    private boolean validFields() {
        return !usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty();
    }

    private boolean validateLogin() {
        CustomerEntity customer = customerDao.getConnectedUser(usernameTextField.getText(), passwordField.getText());
        AdminEntity admin = adminDao.getConnectedAdmin(usernameTextField.getText(),passwordField.getText());
        TravelCompanyEntity travelCompany = travelCompanyDAO.getConnectedUser(usernameTextField.getText(),passwordField.getText());
        CashierEntity cashier = cashierDAO.getConnectedUser(usernameTextField.getText(),passwordField.getText());
        DistributorEntity distributor = distributorDAO.getConnectedUser(usernameTextField.getText(),passwordField.getText());

        if (customer != null) {
            CurrentUser.setUser(customer);
            return true;
        }
        if(admin != null){
            CurrentUser.setUser(admin);
            return true;
        }
        if(travelCompany != null){
            CurrentUser.setUser(travelCompany);
            return true;
        }
        if(cashier != null){
            CurrentUser.setUser(cashier);
            return true;
        }
        if(distributor != null){
            CurrentUser.setUser(distributor);
            return true;
        }

        return false;
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
        for(Integer i : travelDAO.removeExpiredTrips(CurrentTime.getTime())){
            travelDAO.deleteData(i);
        }
    }

}