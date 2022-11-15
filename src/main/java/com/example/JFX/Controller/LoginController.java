package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.HibernateOracle.DAO.AdminDao;
import com.example.HibernateOracle.DAO.CustomerDao;
import com.example.HibernateOracle.Model.AdminEntity;
import com.example.HibernateOracle.Model.CustomerEntity;
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

    private final CustomerDao customerDao = new CustomerDao();
    private final AdminDao adminDao = new AdminDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        if (customer != null) {
            CurrentUser.setUser(customer);
            return true;
        }
        if(admin != null){
            CurrentUser.setUser(admin);
            return true;
        }

        return false;
    }

    private void exit() {
        buttonExit.setOnAction(SceneController::close);
    }

    private void setTexts(){
        File lockFile = new File("Images/KeyLockImage.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

}