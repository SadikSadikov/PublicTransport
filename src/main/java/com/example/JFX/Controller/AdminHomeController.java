package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.HibernateOracle.DAO.*;
import com.example.HibernateOracle.Model.*;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    @FXML
    public Button buttonLogout;
    @FXML
    public ImageView logoutImageView;
    @FXML
    public Label welcomeLabel;
    @FXML
    public ImageView userImageView;
    @FXML
    public Button buttonDashboard;
    @FXML
    public ImageView dashboardImageView;
    @FXML
    public Button buttonExit;
    @FXML
    public Button buttonAddEmployee;
    @FXML
    public ImageView addEmployeeImageView;
    @FXML
    public AnchorPane dashboardAnchorPane;
    @FXML
    public Label titleLabel;
    @FXML
    public ImageView busIconImageView;
    @FXML
    public AnchorPane addEmployeeAnchorPane;
    @FXML
    public ComboBox<String> comboBoxAddEmployee;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public TextField userNameTextField;
    @FXML
    public ImageView addEmployeeIconImageView;
    @FXML
    public Label nameEmployeeLabel;
    @FXML
    public Button createEmployeeButton;
    @FXML
    public Button resetTextButton;
    @FXML
    public TextField travelCompanyTextField;
    @FXML
    public ImageView travelCompanyImageView;
    @FXML
    public TextField distributorTextField;
    @FXML
    public ImageView distributorImageView;
    @FXML
    public TextField cashierTextField;
    @FXML
    public ImageView cashierImageView;
    @FXML
    public Label messageLabel;
    @FXML
    public Label confirmPasswordLabel;
    @FXML
    public Label successfullyLabel;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView refreshImageView;

    private final CustomerDAO customerDao = new CustomerDAO();
    private final AdminDAO adminDao = new AdminDAO();
    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();
    private final CashierDAO cashierDAO = new CashierDAO();
    private final DistributorDAO distributorDAO = new DistributorDAO();
    private Logger logger = LogManager.getLogger();
    private String choiceComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit();
        setTexts();
    }
    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        SceneController.getLoginScene(actionEvent);
    }
    @FXML
    public void buttonDashboard(ActionEvent actionEvent) {
        addEmployeeAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(true);
    }
    @FXML
    public void buttonAddEmployee(ActionEvent actionEvent) {
        dashboardAnchorPane.setVisible(false);
        addEmployeeAnchorPane.setVisible(true);
    }
    @FXML
    public void comboBoxAddEmployee(ActionEvent actionEvent) {
        choiceComboBox = comboBoxAddEmployee.getSelectionModel().getSelectedItem().toString();
    }
    @FXML
    public void createEmployeeButton(ActionEvent actionEvent) {
        if(!isValidFields()){
            messageLabel.setText("Fields can't be empty!");
            return;
        }

        if(!isCheckForEqualPassword()){
            confirmPasswordLabel.setText("Password must be same!");
            return;
        }

        if (!isValidateLogin()) {
            messageLabel.setText("Existing Username and Password!");
            return;
        }

        if(isCreated()){
            confirmPasswordLabel.setText("");
            messageLabel.setText("");
            successfullyLabel.setText("Successfully created!");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event2 -> {
                logger.info("Successfully created account with username: " + userNameTextField.getText());
                successfullyLabel.setText("");

            });
            delay.play();
        }
    }
    @FXML
    public void resetTextButton(ActionEvent actionEvent) {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        userNameTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        messageLabel.setText("");
        confirmPasswordLabel.setText("");
    }
    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
        SceneController.getAdminHomeScene(actionEvent);
    }

    private void setTexts(){
        welcomeLabel.setText("Welcome, " + "\n"+CurrentUser.getUser().getUserName());

        ObservableList<String> addEmployeeList = FXCollections.observableArrayList("TravelCompany","Distributor","Cashier");
        comboBoxAddEmployee.setItems(addEmployeeList);

        cashierTextField.setText(getNumberOfCashier());
        distributorTextField.setText(getNumberOfDistributor());
        travelCompanyTextField.setText(getNumberOfTC());

        File logoutFile = new File("Images/Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);

        File userFile = new File("Images/user.png");
        Image userImage = new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);

        File dashboardFile = new File("Images/dashboard.png");
        Image dashboardImage = new Image(dashboardFile.toURI().toString());
        dashboardImageView.setImage(dashboardImage);

        File busFile = new File("Images/bus.png");
        Image busImage = new Image(busFile.toURI().toString());
        busIconImageView.setImage(busImage);

        File addEmployeeFile = new File("Images/addEmployee.png");
        Image addEmployeeImage = new Image(addEmployeeFile.toURI().toString());
        addEmployeeImageView.setImage(addEmployeeImage);

        File addEmployeeIconFile = new File("Images/addEmployee.png");
        Image addEmployeeIconImage = new Image(addEmployeeIconFile.toURI().toString());
        addEmployeeIconImageView.setImage(addEmployeeIconImage);

        File travelCompanyIconFile = new File("Images/travelcompany.png");
        Image travelCompanyIconImage = new Image(travelCompanyIconFile.toURI().toString());
        travelCompanyImageView.setImage(travelCompanyIconImage);

        File distributorFile = new File("Images/distributorIcon.png");
        Image distributorImage = new Image(distributorFile.toURI().toString());
        distributorImageView.setImage(distributorImage);

        File cashierFile = new File("Images/cashierIcon.png");
        Image cashierImage = new Image(cashierFile.toURI().toString());
        cashierImageView.setImage(cashierImage);

        File refreshFile = new File("Images/refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

    }
    private void exit() {
        buttonExit.setOnAction(SceneController::close);
    }
    private boolean isValidFields() {
        return !firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty() &&
                !userNameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()&&
                !confirmPasswordField.getText().isEmpty();
    }

    private boolean isValidateLogin() {
        CustomerEntity customer = customerDao.getConnectedUser(userNameTextField.getText(), passwordField.getText());
        AdminEntity admin = adminDao.getConnectedAdmin(userNameTextField.getText(),passwordField.getText());
        TravelCompanyEntity travelCompany = travelCompanyDAO.getConnectedUser(userNameTextField.getText(),passwordField.getText());
        CashierEntity cashier = cashierDAO.getConnectedUser(userNameTextField.getText(),passwordField.getText());
        DistributorEntity distributor = distributorDAO.getConnectedUser(userNameTextField.getText(),passwordField.getText());
        if(customer != null){
            return false;
        }
        if(admin != null){
            return false;
        }
        if(travelCompany != null){
            return false;
        }
        if(cashier != null){
            return false;
        }
        if(distributor != null){
            return false;
        }
        return true;
    }

    private boolean isCheckForEqualPassword(){
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    private boolean isCreated(){
        if(choiceComboBox.equals("TravelCompany")){
            return (travelCompanyDAO.addData(new TravelCompanyEntity(firstNameTextField.getText(),
                    lastNameTextField.getText(),userNameTextField.getText(),passwordField.getText())));
        }
        if(choiceComboBox.equals("Distributor")){
            return  distributorDAO.addData(new DistributorEntity(firstNameTextField.getText(),
                    lastNameTextField.getText(),userNameTextField.getText(),passwordField.getText()));
        }
        if(choiceComboBox.equals("Cashier")){
            return cashierDAO.addData(new CashierEntity(firstNameTextField.getText(),
                    lastNameTextField.getText(),userNameTextField.getText(),passwordField.getText()));
        }

        return false;
    }

    private String getNumberOfCashier(){
        return String.valueOf(cashierDAO.getNumberOfCashier());
    }
    private String getNumberOfTC(){
        return String.valueOf(travelCompanyDAO.getNumberOfTravelCompany());
    }
    private String getNumberOfDistributor(){
        return String.valueOf(distributorDAO.getNumberOfDistributor());
    }


}