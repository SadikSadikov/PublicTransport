package com.example.JFX.Controller;

import com.example.HibernateOracle.DAO.*;
import com.example.HibernateOracle.Model.*;
import com.example.Service.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReferenceController implements Initializable {

    @FXML
    public TableView<CustomerEntity> customerTableView;
    @FXML
    public TableColumn<CustomerEntity,String> usernameCColumn;
    @FXML
    public TableColumn<CustomerEntity, LocalDate> dateCColumn;
    @FXML
    public Button buttonShowCustomers;
    @FXML
    public TableView<DistributorEntity> distributorTableView;
    @FXML
    public TableColumn<DistributorEntity,String> usernameDColumn;
    @FXML
    public TableColumn<DistributorEntity,LocalDate> dateDColumn;
    @FXML
    public Button buttonShowDistributors;
    @FXML
    public TableView<CashierEntity> cashierTableView;
    @FXML
    public TableColumn<CashierEntity,String> usernameCAColumn;
    @FXML
    public TableColumn<CashierEntity,LocalDate> dateCAColumn;
    @FXML
    public Button buttonShowCashiers;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView refreshImageView;
    @FXML
    public Button backButton;
    @FXML
    public DatePicker fromDatePicker;
    @FXML
    public DatePicker toDatePicker;
    @FXML
    public ImageView backImageView;
    @FXML
    public TableView<PurchasedTicketsEntity> purchasedTicketsTable;
    @FXML
    public TableColumn<PurchasedTicketsEntity,Long> idPurchasedTicketsColumn;
    @FXML
    public TableColumn<PurchasedTicketsEntity,Long>idTravelColumn;
    @FXML
    public TableColumn<PurchasedTicketsEntity,Long> idCustomerColumn;
    @FXML
    public TableColumn<PurchasedTicketsEntity,Long> numberOfTicketsColumn;
    @FXML
    public Button buttonShowPurchasedTickets;
    @FXML
    public TableColumn<PurchasedTicketsEntity,LocalDate> datePurchasedTicketsColumn;
    @FXML
    public Button buttonShowTravels;
    @FXML
    public TableColumn<TravelEntity,LocalDate> dateTravelsColumn;
    @FXML
    public TableColumn<TravelEntity,Double> priceColumn;
    @FXML
    public TableColumn<TravelEntity,Integer> ticketsColumn;
    @FXML
    public TableColumn<TravelEntity,String> terminalStationColumn;
    @FXML
    public TableColumn<TravelEntity,String> startingStationColumn;
    @FXML
    public TableColumn<TravelEntity,String> typeOfTravelColumn;
    @FXML
    public TableColumn<TravelEntity,Integer> idTravelsColumn;
    @FXML
    public TableView<TravelEntity> travelsTableView;

    private final CustomerService customerService = new CustomerService();
    private final DistributorService distributorService = new DistributorService();
    private final CashierService cashierService = new CashierService();
    private final PurchasedTicketsService purchasedTicketsService = new PurchasedTicketsService();
    private final TravelService travelService = new TravelService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
    }

    @FXML
    public void buttonShowCustomers(ActionEvent actionEvent) {
        if(!isValidFields()){
            return;
        }
        fillCustomerTableWithData();
    }

    @FXML
    public void buttonShowDistributors(ActionEvent actionEvent) {
        if(!isValidFields()){
            return;
        }
        fillDistributorTableWithData();
    }

    @FXML
    public void buttonShowCashiers(ActionEvent actionEvent) {
        if(!isValidFields()){
            return;
        }
        fillCashierTableWithData();
    }


    @FXML
    public void backButton(ActionEvent actionEvent) throws IOException {
        SceneController.getAdminHomeScene(actionEvent);
    }

    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
        SceneController.getUsersScene(actionEvent);
    }

    @FXML
    public void buttonShowPurchasedTickets(ActionEvent actionEvent) {
        fillPurchasedTicketsTableWithData();
    }

    @FXML
    public void buttonShowTravels(ActionEvent actionEvent) {
        fillTravelsTableWithData();
    }

    private void setText(){
        File refreshFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

        File backFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\back.png");
        Image backImage = new Image(backFile.toURI().toString());
        backImageView.setImage(backImage);



    }

    private void fillCustomerTableWithData(){
        usernameCColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        dateCColumn.setCellValueFactory(new PropertyValueFactory<>("date_customer"));

        customerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        customerTableView.setItems(getCustomerObservableList());
    }

    private ObservableList<CustomerEntity> getCustomerObservableList(){
        ObservableList<CustomerEntity> customers = FXCollections.observableArrayList();
        customers.addAll(customerService.getCustomerWithDate(Date.valueOf(fromDatePicker.getValue()).toLocalDate(),Date.valueOf(toDatePicker.getValue()).toLocalDate()));
        return customers;
    }

    private void fillDistributorTableWithData(){
        usernameDColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        dateDColumn.setCellValueFactory(new PropertyValueFactory<>("date_distributor"));

        distributorTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        distributorTableView.setItems(getDistributorObservableList());
    }

    private ObservableList<DistributorEntity> getDistributorObservableList(){
        ObservableList<DistributorEntity> distributors = FXCollections.observableArrayList();
        distributors.addAll(distributorService.getDistributorWithDate(Date.valueOf(fromDatePicker.getValue()).toLocalDate(),Date.valueOf(toDatePicker.getValue()).toLocalDate()));
        return distributors;
    }

    private void fillCashierTableWithData(){
        usernameCAColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        dateCAColumn.setCellValueFactory(new PropertyValueFactory<>("date_cashier"));

        cashierTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cashierTableView.setItems(getCashierObservableList());
    }

    private ObservableList<CashierEntity> getCashierObservableList(){
        ObservableList<CashierEntity> cashiers = FXCollections.observableArrayList();
        cashiers.addAll(cashierService.getCashierWithDate(Date.valueOf(fromDatePicker.getValue()).toLocalDate(),Date.valueOf(toDatePicker.getValue()).toLocalDate()));
        return cashiers;
    }

    private void fillPurchasedTicketsTableWithData(){
        idPurchasedTicketsColumn.setCellValueFactory(new PropertyValueFactory<>("id_purchasedTickets"));
        idTravelColumn.setCellValueFactory(new PropertyValueFactory<>("id_travel_PT"));
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("id_cashier_PT"));
        numberOfTicketsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfTickets"));
        datePurchasedTicketsColumn.setCellValueFactory(new PropertyValueFactory<>("date_purchasedTickets"));

        purchasedTicketsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        purchasedTicketsTable.setItems(getPurchasedTicketsObservableList());
    }

    private ObservableList<PurchasedTicketsEntity> getPurchasedTicketsObservableList(){
        ObservableList<PurchasedTicketsEntity> tickets = FXCollections.observableArrayList();
        tickets.addAll(purchasedTicketsService.getPurchasedTicketsWithDate(Date.valueOf(fromDatePicker.getValue()).toLocalDate(),Date.valueOf(toDatePicker.getValue()).toLocalDate()));
        return tickets;
    }

    private void fillTravelsTableWithData(){
        idTravelsColumn.setCellValueFactory(new PropertyValueFactory<>("id_travel"));
        typeOfTravelColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfTravel"));
        startingStationColumn.setCellValueFactory(new PropertyValueFactory<>("startingStation"));
        terminalStationColumn.setCellValueFactory(new PropertyValueFactory<>("terminalStation"));
        ticketsColumn.setCellValueFactory(new PropertyValueFactory<>("ticketLimit"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceTicket"));
        dateTravelsColumn.setCellValueFactory(new PropertyValueFactory<>("date_travel"));


        travelsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        travelsTableView.setItems(getTravelObservableList());
    }

    private ObservableList<TravelEntity> getTravelObservableList(){
        ObservableList<TravelEntity> travels = FXCollections.observableArrayList();
        travels.addAll(travelService.getTravelsWithDate(Date.valueOf(fromDatePicker.getValue()).toLocalDate(),Date.valueOf(toDatePicker.getValue()).toLocalDate()));
        return travels;
    }


    private boolean isValidFields() {
        return  !fromDatePicker.getEditor().getText().isEmpty() && !toDatePicker.getEditor().getText().isEmpty();

    }

}
