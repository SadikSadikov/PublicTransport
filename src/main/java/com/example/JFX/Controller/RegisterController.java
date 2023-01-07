package com.example.JFX.Controller;

import com.example.Helpers.Log4j;
import com.example.Service.Classes.AuthenticationService;
import com.example.Service.Classes.CreateUserService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{

    @FXML
    public Button buttonSignUp;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField userNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public ImageView securityImageView;
    @FXML
    public Button buttonReset;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public Label registerMessageLabel;
    @FXML
    public Label passwordNoMatchLabel;
    @FXML
    public Label successfullyRegisteredLabel;
    @FXML
    public Hyperlink backHyperlink;
    @FXML
    public ImageView backImageView;

    private final AuthenticationService authenticationService = new AuthenticationService();
    private final CreateUserService createUserService = new CreateUserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTexts();
    }

    @FXML
    public void buttonSignUp(ActionEvent actionEvent) {
        if(!isValidFields()){
            registerMessageLabel.setText("Fields can't be empty!");
            return;
        }

        if(!isCheckForEqualPassword()){
            passwordNoMatchLabel.setText("Password must be same!");
            return;
        }

        if (!isValidateLogin()) {
            registerMessageLabel.setText("Existing Username and Password!");
            return;
        }

        if(isCreated()){
            passwordNoMatchLabel.setText("");
            registerMessageLabel.setText("");
            successfullyRegisteredLabel.setText("Successfully created!");

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event2 -> {
                try {
                    Log4j.logger.info("Successfully created customer with username: " + userNameTextField.getText());
                    SceneController.getLoginScene(actionEvent);
                } catch (IOException e) {
                    System.out.println(e);
                }
            });
            delay.play();

        }

    }
    @FXML
    public void buttonReset(ActionEvent actionEvent) {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        userNameTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        registerMessageLabel.setText("");
        passwordNoMatchLabel.setText("");
    }
    @FXML
    public void backHyperlink(ActionEvent actionEvent) throws IOException {
        SceneController.getLoginScene(actionEvent);
    }



    private boolean isValidFields() {
        return !firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty() &&
                !userNameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()&&
                !confirmPasswordField.getText().isEmpty();
    }

    private boolean isValidateLogin() {
        return authenticationService.loginUser(userNameTextField.getText(), passwordField.getText()) == null;
    }

    private boolean isCheckForEqualPassword(){
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    private boolean isCreated(){
        String typeCustomer = "Customer";
        return createUserService.createUser(typeCustomer,firstNameTextField.getText(),lastNameTextField.getText(),
                userNameTextField.getText(), passwordField.getText());
    }

    private void setTexts(){
        File securityFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\SecurityIcon.png");
        Image securityImage = new Image(securityFile.toURI().toString());
        securityImageView.setImage(securityImage);

        File backFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\back.png");
        Image backImage = new Image(backFile.toURI().toString());
        backImageView.setImage(backImage);
    }

}
