package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.Helpers.Travels.Destination;
import com.example.Helpers.Travels.ModeOfTransport;
import com.example.Helpers.Travels.Status;
import com.example.Helpers.Travels.TypeOfTravel;
import com.example.HibernateOracle.DAO.CustomerDAO;
import com.example.HibernateOracle.DAO.PurchasedTicketsDAO;
import com.example.HibernateOracle.DAO.TravelDAO;
import com.example.HibernateOracle.Model.*;
import com.example.Service.Classes.CreateUserService;
import com.example.Service.Classes.CustomerService;
import com.example.Service.Classes.PurchasedTicketsService;
import com.example.Service.Classes.TravelService;
import javafx.animation.TranslateTransition;
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
import java.util.*;

public class BuyingTicketsController implements Initializable {

    @FXML
    public Button buyButton;
    @FXML
    public Button searchButton;
    @FXML
    public Label messageLabel;
    @FXML
    public ComboBox<Integer> ticketsComboBox;
    @FXML
    public ComboBox<String> startingStationComboBox;
    @FXML
    public ComboBox<String> terminalStationComboBox;
    @FXML
    public DatePicker dateOfArrivalDatePicker;
    @FXML
    public DatePicker dateOfDepartureDatePicker;
    @FXML
    public ComboBox<String> typeOfTravelComboBox;
    @FXML
    public Button backButton;
    @FXML
    public ComboBox<String> modeOfTransportComboBox;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView refreshImageView;
    @FXML
    public Label ticketsLabel;
    @FXML
    public AnchorPane buyTicketAnchorPane;
    @FXML
    public Label priceLabel;
    @FXML
    public TextField priceTextField;
    @FXML
    public AnchorPane animationAnchorPane;
    @FXML
    public ImageView stickManImageView;
    @FXML
    public ImageView cartoonTicketImageView;
    @FXML
    public CheckBox animationCheckBox;
    @FXML
    public TableView<TravelEntity> travelTableView;
    @FXML
    public TableColumn<TravelEntity,Long> idTravelColumn;
    @FXML
    public TableColumn<TravelEntity,Double> priceColumn;

    private final TravelService travelService = new TravelService();
    private final CustomerService customerService = new CustomerService();
    private final PurchasedTicketsService purchasedTicketsService = new PurchasedTicketsService();

    private String typeOfTravelString;
    private String startingStationString;
    private String terminalStationString;
    private int ticketsInteger;
    private String modeOfTransportString;
    private int idTravel = 0;


    @FXML
    public void backButton(ActionEvent actionEvent) throws IOException {
        SceneController.getCustomerHomeScene(actionEvent);
    }

    @FXML
    public void searchButton(ActionEvent actionEvent) {

        if(!isValidFields()){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Fields can't be empty!");
            return;
        }
        int size = travelService.searchTicketsToBuy(typeOfTravelString,Date.valueOf(dateOfDepartureDatePicker.getValue()).toLocalDate() ,Date.valueOf(dateOfArrivalDatePicker.getValue()).toLocalDate(),startingStationString,terminalStationString,modeOfTransportString,Status.TRUE.getStatus()).size();
        if(size != 0){
            messageLabel.setTextFill(Color.GREEN);

            messageLabel.setText("Found travel - " + size);

            fillTravelTableWithData();

            buyTicketAnchorPane.setVisible(true);
        }
        else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Not found travel");

        }
    }
    @FXML
    public void buyButton(ActionEvent actionEvent) {

        if(ticketsInteger <= travelService.getTotalTicketsWithID(idTravel)){

            int remainingTickets = travelService.getTotalTicketsWithID(idTravel) - ticketsInteger;

            int totalTicketsForCustomer = customerService.getTotalTicketsWithID(((CustomerEntity) CurrentUser.getUser()).getCustomer_id());

            totalTicketsForCustomer+=ticketsInteger;

            customerService.updateTotalTickets(totalTicketsForCustomer,((CustomerEntity)CurrentUser.getUser()).getCustomer_id());

            travelService.updateTicketNumber(idTravel,remainingTickets);

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Successfully purchased");

            purchasedTicketsService.addPurchasedTickets(idTravel,travelService.getIdCashierWithID(idTravel),((CustomerEntity)CurrentUser.getUser()).getCustomer_id(),ticketsInteger);

            Log4j.logger.info("\n" +
                    "Purchased ticket from " + CurrentUser.getUser().getUserName());
        }
        else{
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Sorry we don't have that many tickets");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
        animation();

    }

    @FXML
    public void modeOfTransportComboBox(ActionEvent actionEvent) {
        modeOfTransportString = modeOfTransportComboBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void typeOfTravelComboBox(ActionEvent actionEvent) {
        typeOfTravelString = typeOfTravelComboBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void startingStationComboBox(ActionEvent actionEvent) {
        startingStationString = startingStationComboBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void terminalStationComboBox(ActionEvent actionEvent) {
        terminalStationString = terminalStationComboBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void ticketsComboBox(ActionEvent actionEvent) {
        ticketsInteger = ticketsComboBox.getSelectionModel().getSelectedItem();

        if(isValidFields() && idTravel != 0){
            double priceForTickets = travelService.getPriceTicketWithID(idTravel) * ticketsInteger;
            priceTextField.setText(String.valueOf(String.format("%.2f",priceForTickets)));
        }

    }
    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
        SceneController.getBuyingTicketsScene(actionEvent);

    }

    @FXML
    public void animationCheckBox(ActionEvent actionEvent) {
        if(!animationCheckBox.isSelected()){
            animationAnchorPane.setVisible(false);
        }
        else{
           animationAnchorPane.setVisible(true);
        }

    }

    @FXML
    public void clickTakeIdForBuy(MouseEvent mouseEvent) {
        try{
            ObservableList<TravelEntity> travelEntityObservableList;
            travelEntityObservableList = travelTableView.getSelectionModel().getSelectedItems();

            idTravel = travelEntityObservableList.get(0).getId_travel();

            if(ticketsComboBox.getValue() == null){
                ticketsInteger = ticketsComboBox.getItems().get(0);
            }

            double priceForTickets = travelService.getPriceTicketWithID(idTravel) * ticketsInteger;

            priceTextField.setText(String.valueOf(String.format("%.2f",priceForTickets)));
        }
        catch (Exception e){
            System.out.println(e);
        }

    }


    private void setText(){

        ObservableList<String> typeOfTravelList = FXCollections.observableArrayList(TypeOfTravel.ONE_WAY.getType(),TypeOfTravel.TWO_WAY.getType());
        typeOfTravelComboBox.setItems(typeOfTravelList);

        ObservableList<String> destinationList = FXCollections.observableArrayList(Destination.VARNA.getDestination(),
                Destination.SOFIA.getDestination(),Destination.SHUMEN.getDestination(),Destination.DOBRICH.getDestination());

        startingStationComboBox.setItems(destinationList);
        terminalStationComboBox.setItems(destinationList);

        ObservableList<String> modeOfTransportList = FXCollections.observableArrayList(ModeOfTransport.BUS.getMode(),ModeOfTransport.TRAIN.getMode());
        modeOfTransportComboBox.setItems(modeOfTransportList);

        List<Integer> ticketNumber = new ArrayList<>(50);
        for(int i = 1;i <= 50;i++){
            ticketNumber.add(i);
        }

        ticketsComboBox.getItems().addAll(ticketNumber);

        animationCheckBox.setSelected(true);


        File refreshFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

        File cartoonTicketFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\cartoonTicket.png");
        Image cartoonTicketImage = new Image(cartoonTicketFile.toURI().toString());
        cartoonTicketImageView.setImage(cartoonTicketImage);

        File stickManFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\stickMan.png");
        Image stickManImage = new Image(stickManFile.toURI().toString());
        stickManImageView.setImage(stickManImage);


    }


    private boolean isValidFields() {
        return !typeOfTravelComboBox.getItems().isEmpty() && !startingStationComboBox.getItems().isEmpty() && !terminalStationComboBox.getItems().isEmpty() &&
                !dateOfArrivalDatePicker.getEditor().getText().isEmpty()&&
                !dateOfDepartureDatePicker.getEditor().getText().isEmpty() && !modeOfTransportComboBox.getItems().isEmpty();
    }

    private void animation(){
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(cartoonTicketImageView);
        transition.setDuration(Duration.millis(1000));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setByX(250);
        transition.play();
    }

    private void fillTravelTableWithData(){
        idTravelColumn.setCellValueFactory(new PropertyValueFactory<>("id_travel"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceTicket"));


        travelTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        travelTableView.setItems(getTravelObservableList());

    }

    private ObservableList<TravelEntity> getTravelObservableList(){
        ObservableList<TravelEntity> travels = FXCollections.observableArrayList();
        travels.addAll(travelService.searchTicketsToBuy(typeOfTravelString,Date.valueOf(dateOfDepartureDatePicker.getValue()).toLocalDate() ,Date.valueOf(dateOfArrivalDatePicker.getValue()).toLocalDate(),startingStationString,terminalStationString,modeOfTransportString,Status.TRUE.getStatus()));
        return travels;
    }



}
