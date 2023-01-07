package com.example.JFX.Controller;

import com.example.Helpers.CurrentTime;
import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.Helpers.Travels.UnsoldTicketsNotification;
import com.example.Service.Classes.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    @FXML
    public Button buttonReferences;
    @FXML
    public ImageView usersImageView;
    @FXML
    public BarChart purchasedTicketsChart;

    private final AuthenticationService authenticationService = new AuthenticationService();
    private final CreateUserService createUserService = new CreateUserService();
    private final CashierService cashierService = new CashierService();
    private final DistributorService distributorService = new DistributorService();
    private final TravelCompanyService travelCompanyService = new TravelCompanyService();
    private final CustomerService customerService = new CustomerService();
    private final TravelService travelService = new TravelService();
    private final PurchasedTicketsService purchasedTicketsService = new PurchasedTicketsService();

    private String choiceComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit();
        setTexts();
        systemNotification();



    }
    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        Log4j.logger.warn("User named:" + CurrentUser.getUser().getUserName() + " has logged out ");
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
            Log4j.logger.info("Successfully created account with username: " + userNameTextField.getText());
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event2 -> {
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

    @FXML
    public void buttonReferences(ActionEvent actionEvent) throws IOException {
        SceneController.getUsersScene(actionEvent);
    }


    private void setTexts(){
        fillPurchasedTicketsChart();

        welcomeLabel.setText("Welcome, " + "\n"+CurrentUser.getUser().getUserName());

        ObservableList<String> addEmployeeList = FXCollections.observableArrayList("TravelCompany","Distributor","Cashier");
        comboBoxAddEmployee.setItems(addEmployeeList);

        cashierTextField.setText(getNumberOfCashier());
        distributorTextField.setText(getNumberOfDistributor());
        travelCompanyTextField.setText(getNumberOfTC());

        File logoutFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);

        File userFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\user.png");
        Image userImage = new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);

        File dashboardFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\dashboard.png");
        Image dashboardImage = new Image(dashboardFile.toURI().toString());
        dashboardImageView.setImage(dashboardImage);

        File busFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\bus.png");
        Image busImage = new Image(busFile.toURI().toString());
        busIconImageView.setImage(busImage);

        File addEmployeeFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\addEmployee.png");
        Image addEmployeeImage = new Image(addEmployeeFile.toURI().toString());
        addEmployeeImageView.setImage(addEmployeeImage);

        File addEmployeeIconFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\addEmployee.png");
        Image addEmployeeIconImage = new Image(addEmployeeIconFile.toURI().toString());
        addEmployeeIconImageView.setImage(addEmployeeIconImage);

        File travelCompanyIconFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\travelcompany.png");
        Image travelCompanyIconImage = new Image(travelCompanyIconFile.toURI().toString());
        travelCompanyImageView.setImage(travelCompanyIconImage);

        File distributorFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\distributorIcon.png");
        Image distributorImage = new Image(distributorFile.toURI().toString());
        distributorImageView.setImage(distributorImage);

        File cashierFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\cashierIcon.png");
        Image cashierImage = new Image(cashierFile.toURI().toString());
        cashierImageView.setImage(cashierImage);

        File refreshFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);


        File usersFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\user.png");
        Image usersImage = new Image(usersFile.toURI().toString());
        usersImageView.setImage(usersImage);

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
        return authenticationService.loginUser(userNameTextField.getText(), passwordField.getText()) == null;
    }

    private boolean isCheckForEqualPassword(){
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    private boolean isCreated(){
       return createUserService.createUser(choiceComboBox,firstNameTextField.getText(),lastNameTextField.getText(), userNameTextField.getText(), passwordField.getText());
    }

    private String getNumberOfCashier(){
        return String.valueOf(cashierService.getNumberOfCashier());
    }
    private String getNumberOfTC(){
        return String.valueOf(travelCompanyService.getNumberOfTravelCompany());
    }
    private String getNumberOfDistributor(){
        return String.valueOf(distributorService.getNumberOfDistributor());
    }

    private void fillPurchasedTicketsChart(){

        XYChart.Series series = new XYChart.Series();

        series.setName(CurrentTime.getTime().toString());

        purchasedTicketsChart.getData().addAll(customerService.getTop5CustomerMostPurchasedTickets(series));

    }

    private void systemNotification(){
        int count = UnsoldTicketsNotification.unsoldTicketsNotification(CurrentTime.getTime(),travelService.getTravelsWithUnsoldTickets());
        if(count != 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Travel Status:");
            alert.setContentText("You have unsold tickets" + " - " + count);
            alert.show();
        }



        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 15);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int sumPurchasedTickets = 0;
                    for(Integer i:purchasedTicketsService.getTotalPurchasedTickets()){
                        sumPurchasedTickets += i;
                    }

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Travel Status:");
                    alert.setContentText("Purchased tickets" + " - " + sumPurchasedTickets);
                    alert.show();

                });
                timer.purge();
                timer.cancel();

            }
        }, calendar.getTime(), 86400000);
        //24hrs == 86400000ms
    }


}
