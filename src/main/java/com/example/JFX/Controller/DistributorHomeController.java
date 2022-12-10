package com.example.JFX.Controller;

import com.example.Helpers.CurrentTime;
import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.Helpers.NotificationSystem.ReceivingNotifications;
import com.example.Helpers.NotificationsPath;
import com.example.Helpers.Travels.UnsoldTicketsNotification;
import com.example.HibernateOracle.DAO.CashierDAO;
import com.example.HibernateOracle.DAO.MessagesDAO;
import com.example.HibernateOracle.DAO.TravelCompanyDAO;
import com.example.HibernateOracle.DAO.TravelDAO;
import com.example.HibernateOracle.Model.MessagesEntity;
import com.example.HibernateOracle.Model.TravelEntity;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DistributorHomeController implements Initializable {

    @FXML
    public ImageView logoutImageView;
    @FXML
    public Button buttonLogout;
    @FXML
    public Button buttonExit;
    @FXML
    public Label titleLabel;
    @FXML
    public ImageView busIconImageView;
    @FXML
    public ImageView distributorImageView;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Button buttonDashboard;
    @FXML
    public ImageView dashboardImageView;
    @FXML
    public Button buttonRequestTickets;
    @FXML
    public AnchorPane dashboardAnchorPane;
    @FXML
    public AnchorPane requestTicketsAnchorPane;
    @FXML
    public ImageView ticketsImageView;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView refreshImageView;
    @FXML
    public TableColumn<TravelEntity,String> typeOfTravelTC;
    @FXML
    public TableColumn<TravelEntity,String> startingStationTC;
    @FXML
    public TableColumn<TravelEntity,String> dateOfDepartureTC;
    @FXML
    public TableColumn<TravelEntity,String> dateOfArrivalTC;
    @FXML
    public TableColumn<TravelEntity,Long> numberOfPlacesTC;
    @FXML
    public TableColumn<TravelEntity,String> modeOfTransportTC;
    @FXML
    public TableColumn<TravelEntity,Long> ticketLimitTC;
    @FXML
    public TableView<TravelEntity> travelTable;
    @FXML
    public TableColumn<TravelEntity,Long> idTravel;
    @FXML
    public ComboBox<String> choiceCashierComboBox;
    @FXML
    public Button addDataButton;
    @FXML
    public Label messageLabel;
    @FXML
    public Button buttonTravels;
    @FXML
    public ImageView travelsImageView;
    @FXML
    public AnchorPane travelsAnchorPane;
    @FXML
    public DatePicker fromDatePicker;
    @FXML
    public DatePicker toDatePicker;
    @FXML
    public Button buttonShow;
    @FXML
    public TableView<TravelEntity> travelsTable;
    @FXML
    public TableColumn<TravelEntity,String> modeOfTransportColumn;
    @FXML
    public TableColumn<TravelEntity,String> startingStationColumn;
    @FXML
    public TableColumn<TravelEntity,Long> idColumn;
    @FXML
    public TableColumn<TravelEntity, LocalDate> dateColumn;
    @FXML
    public ComboBox<String> travelCompanyComboBox;

    private final TravelDAO travelDAO = new TravelDAO();
    private final CashierDAO cashierDAO = new CashierDAO();
    private final TravelCompanyDAO travelCompanyDAO = new TravelCompanyDAO();
    private final MessagesDAO messagesDAO = new MessagesDAO();


    private String choiceTC;
    private String choiceCashier;
    private int idTC = 0;
    private int travelId = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit();
        setTexts();
        fillTableWithData();
        notifications();
        systemNotification();


    }

    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        Log4j.logger.warn("User named:" + CurrentUser.getUser().getUserName() + " has logged out ");
        SceneController.getLoginScene(actionEvent);
    }
    @FXML
    public void buttonDashboard(ActionEvent actionEvent) {
        travelsAnchorPane.setVisible(false);
        requestTicketsAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(true);
    }
    @FXML
    public void buttonRequestTickets(ActionEvent actionEvent) {
        travelsAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(false);
        requestTicketsAnchorPane.setVisible(true);
    }
    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
        SceneController.getDistributorScene(actionEvent);
    }
    @FXML
    public void choiceCashierComboBox(ActionEvent actionEvent) {
        choiceCashier = choiceCashierComboBox.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void addDataButton(ActionEvent actionEvent) {
        if(!isValidFields()){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Fields can't be empty!");
            return;
        }
        if(travelId == 0 && idTC == 0){
            return;
        }
        messageLabel.setTextFill(Color.GREEN);
        messageLabel.setText("Successfully requested");
        messagesDAO.addData(new MessagesEntity(choiceCashier,"null",travelId,idTC));

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event2 -> {
            messageLabel.setText("");
        });
        delay.play();


    }
    @FXML
    public void clickTakeRow(MouseEvent mouseEvent) {
        ObservableList<TravelEntity> travelEntityObservableList;
        travelEntityObservableList = travelTable.getSelectionModel().getSelectedItems();

        travelId = travelEntityObservableList.get(0).getId_travel();
        idTC = travelEntityObservableList.get(0).getId_tc();

    }

    @FXML
    public void buttonTravels(ActionEvent actionEvent) {
        dashboardAnchorPane.setVisible(false);
        requestTicketsAnchorPane.setVisible(false);
        travelsAnchorPane.setVisible(true);

    }

    @FXML
    public void buttonShow(ActionEvent actionEvent) {
        fillTravelsDateTableWithData();
    }

    @FXML
    public void travelCompanyComboBox(ActionEvent actionEvent) {
        choiceTC = travelCompanyComboBox.getSelectionModel().getSelectedItem();

    }


    private void setTexts(){


        ObservableList<String> cashierChoice = FXCollections.observableArrayList(cashierDAO.getNamesOfCashier());
        choiceCashierComboBox.setItems(cashierChoice);

        ObservableList<String> tcChoice = FXCollections.observableArrayList(travelCompanyDAO.getNamesOfTC());
        travelCompanyComboBox.setItems(tcChoice);

        File logoutFile = new File("Images/Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);

        File distributorFile = new File("Images/distributorIcon.png");
        Image distributorImage = new Image(distributorFile.toURI().toString());
        distributorImageView.setImage(distributorImage);

        File busFile = new File("Images/bus.png");
        Image busImage = new Image(busFile.toURI().toString());
        busIconImageView.setImage(busImage);

        File dashboardFile = new File("Images/dashboard.png");
        Image dashboardImage = new Image(dashboardFile.toURI().toString());
        dashboardImageView.setImage(dashboardImage);

        File ticketsFile = new File("Images/plane-tickets.png");
        Image ticketsImage = new Image(ticketsFile.toURI().toString());
        ticketsImageView.setImage(ticketsImage);

        File refreshFile = new File("Images/refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

        File travelCompanyIconFile = new File("Images/travelcompany.png");
        Image travelCompanyIconImage = new Image(travelCompanyIconFile.toURI().toString());
        travelsImageView.setImage(travelCompanyIconImage);
    }
    private void exit(){
        buttonExit.setOnAction(SceneController::close);
    }

    private void fillTableWithData(){
        typeOfTravelTC.setCellValueFactory(new PropertyValueFactory<>("typeOfTravel"));
        startingStationTC.setCellValueFactory(new PropertyValueFactory<>("startingStation"));
        dateOfDepartureTC.setCellValueFactory(new PropertyValueFactory<>("dateOfDeparture"));
        dateOfArrivalTC.setCellValueFactory(new PropertyValueFactory<>("dateOfArrival"));
        numberOfPlacesTC.setCellValueFactory(new PropertyValueFactory<>("numberOfPlaces"));
        modeOfTransportTC.setCellValueFactory(new PropertyValueFactory<>("modeOfTransport"));
        ticketLimitTC.setCellValueFactory(new PropertyValueFactory<>("ticketLimit"));
        idTravel.setCellValueFactory(new PropertyValueFactory<>("id_travel"));

        travelTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        travelTable.setItems(getTravelObservableList());
    }

    private ObservableList<TravelEntity> getTravelObservableList(){
        ObservableList<TravelEntity> travels = FXCollections.observableArrayList();
        travels.addAll(travelDAO.getTravels());
        return travels;
    }

    private boolean isValidFields() {
        return !choiceCashierComboBox.getItems().isEmpty() && !(travelId == 0) ;
    }

    private void fillTravelsDateTableWithData(){

        modeOfTransportColumn.setCellValueFactory(new PropertyValueFactory<>("modeOfTransport"));
        startingStationColumn.setCellValueFactory(new PropertyValueFactory<>("startingStation"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_travel"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_travel"));

        travelsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        travelsTable.setItems(getTravelsObservableList());
    }

    private ObservableList<TravelEntity> getTravelsObservableList(){
        ObservableList<TravelEntity> travels = FXCollections.observableArrayList();
        travels.addAll(travelDAO.getTravelsWithDateAndTC(travelCompanyDAO.getIdWithName(choiceTC), Date.valueOf(fromDatePicker.getValue()).toLocalDate(),Date.valueOf(toDatePicker.getValue()).toLocalDate()));
        return travels;
    }

    private void notifications(){
        //when a new trip is added
        List<String> newTravelList = ReceivingNotifications.receivingNotifications(NotificationsPath.DISTRIBUTOR_NEW_TRAVEL_FILE.getPath());
        if(!(newTravelList.isEmpty())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Travel Status:");
            for(String s: newTravelList){
                alert.setContentText(s + " - " + newTravelList.size());

            }
            alert.show();
        }

        //when a trip is cancelled
        List<String> cancelTravelList = ReceivingNotifications.receivingNotifications(NotificationsPath.DISTRIBUTOR_CANCEL_TRAVEL_FILE.getPath());
        if(!(cancelTravelList.isEmpty())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Travel Status:");
            for(String s: cancelTravelList){
                alert.setContentText(s + " - " + cancelTravelList.size());

            }
            alert.show();
        }


    }

    private void systemNotification(){
        int count = UnsoldTicketsNotification.unsoldTicketsNotification(CurrentTime.getTime(),travelDAO.getTravelsWithUnsoldTickets());
        if(count != 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Travel Status:");
            alert.setContentText("You have unsold tickets" + " - " + count);
            alert.show();
        }
    }

}
