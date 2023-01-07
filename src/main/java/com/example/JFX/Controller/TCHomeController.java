package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.Helpers.NotificationSystem.SendingNotifications;
import com.example.Helpers.NotificationsPath;
import com.example.Helpers.Travels.*;
import com.example.HibernateOracle.DAO.CashierDAO;
import com.example.HibernateOracle.DAO.MessagesDAO;
import com.example.HibernateOracle.DAO.TravelDAO;
import com.example.HibernateOracle.Model.MessagesEntity;
import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Model.TravelEntity;
import com.example.Service.Classes.CashierService;
import com.example.Service.Classes.MessagesService;
import com.example.Service.Classes.TravelService;
import javafx.animation.PauseTransition;
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
import java.util.ResourceBundle;

public class TCHomeController implements Initializable {
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
    public ImageView refreshImageView;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView travelCompanyImageView;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Button addTravelButton;
    @FXML
    public ImageView travelImageView;
    @FXML
    public Button dashboardButton;
    @FXML
    public ImageView dashboardImageView;
    @FXML
    public AnchorPane dashboardAnchorPane;
    @FXML
    public AnchorPane addTravelAnchorPane;
    @FXML
    public ComboBox<String> comboBoxTypeOfTravel;
    @FXML
    public ComboBox<String> comboBoxStartingStation;
    @FXML
    public ComboBox<String> comboBoxTerminalStation;
    @FXML
    public DatePicker dateOfDepartureDatePicker;
    @FXML
    public DatePicker dateOfArrivalDatePicker;
    @FXML
    public TextField numberOfPlacesTextField;
    @FXML
    public ComboBox<String> comboBoxModeOfTransport;
    @FXML
    public TextField ticketLimitTextField;
    @FXML
    public Button addTravel;
    @FXML
    public Button resetButton;
    @FXML
    public Label messageLabel;
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
    public TableColumn<TravelEntity,String> statusTC;
    @FXML
    public TableColumn<TravelEntity,Long> ticketLimitTC;
    @FXML
    public TableView<TravelEntity> travelTable;
    @FXML
    public TableColumn<TravelEntity,Long> idTC;
    @FXML
    public TableColumn<TravelEntity,Long> cashierTC;
    @FXML
    public AnchorPane messagesAnchorPane;
    @FXML
    public TableColumn<MessagesEntity,Long> idMessage;
    @FXML
    public TableColumn<MessagesEntity,String> requestMessage;
    @FXML
    public TableColumn<MessagesEntity,String> answerMessage;
    @FXML
    public Button answerButton;
    @FXML
    public Button buttonMessages;
    @FXML
    public ImageView messageImageView;
    @FXML
    public TableView<MessagesEntity> messageTable;
    @FXML
    public TableColumn<MessagesEntity,Long> idTravelMessage;
    @FXML
    public Button deleteMessageButton;
    @FXML
    public TextField priceTicketTextField;
    @FXML
    public Button buttonCancelTravel;
    @FXML
    public Label cancelledMessageLabel;

    private String typeOfTravel;
    private String terminalStation;
    private String startingStation;
    private String modeOfTransport;

    private final TravelService travelService = new TravelService();
    private final MessagesService messagesService = new MessagesService();
    private final CashierService cashierService = new CashierService();

    private int messageID = 0;
    private String cashierName;
    private int idTravel;
    private int idTravelForDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit();
        setTexts();
        fillTravelTableWithData();
        fillMessageTableWithData();
    }

    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        Log4j.logger.warn("User named:" + CurrentUser.getUser().getUserName() + " has logged out ");
        SceneController.getLoginScene(actionEvent);
    }
    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
         SceneController.getTravelCompanyHomeScene(actionEvent);
    }
    @FXML
    public void addTravelButton(ActionEvent actionEvent) {
        messagesAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(false);
        addTravelAnchorPane.setVisible(true);

    }
    @FXML
    public void dashboardButton(ActionEvent actionEvent) {
        messagesAnchorPane.setVisible(false);
        addTravelAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(true);

    }
    @FXML
    public void comboBoxTypeOfTravel(ActionEvent actionEvent) {
        typeOfTravel = comboBoxTypeOfTravel.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void comboBoxStartingStation(ActionEvent actionEvent) {
        startingStation = comboBoxStartingStation.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void comboBoxTerminalStation(ActionEvent actionEvent) {
        terminalStation = comboBoxTerminalStation.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void comboBoxModeOfTransport(ActionEvent actionEvent) {
        modeOfTransport = comboBoxModeOfTransport.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void addTravel(ActionEvent actionEvent) {

        if(!isValidFields()){
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Fields can't be empty!");
            return;
        }
        if(!isValidTicketLimit()){
            messageLabel.setText("Ticket limit cannot be greater than the number of seats");
        }
        if(isCreated()){
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Successfully added travel");
            SendingNotifications.sendingNotifications(NotificationsPath.DISTRIBUTOR_NEW_TRAVEL_FILE.getPath(), "New travels are added");
            Log4j.logger.info("Successfully created travel");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event2 -> {
                messageLabel.setText("");
            });
            delay.play();
        }


    }


    @FXML
    public void resetButton(ActionEvent actionEvent) {
        messageLabel.setText("");
        numberOfPlacesTextField.setText("");
        ticketLimitTextField.setText("");
        dateOfDepartureDatePicker.getEditor().clear();
        dateOfArrivalDatePicker.getEditor().clear();

    }

    @FXML
    public void answerButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,answerMessage.getText(),ButtonType.YES,ButtonType.CANCEL);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            messagesService.addAnswer(ButtonType.YES.getText(),messageID);
            travelService.addIDCashier(idTravel,cashierService.getIdWithName(cashierName));
            return;
        }
        messagesService.addAnswer(ButtonType.CANCEL.getText(),messageID);
    }

    @FXML
    public void buttonMessages(ActionEvent actionEvent) {
        addTravelAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(false);
        messagesAnchorPane.setVisible(true);
    }
    @FXML
    public void clickTakeRow(MouseEvent mouseEvent) {
        try{
            ObservableList<MessagesEntity> messageEntityObservableList;
            messageEntityObservableList = messageTable.getSelectionModel().getSelectedItems();

            messageID = messageEntityObservableList.get(0).getId_message();
            cashierName = messageEntityObservableList.get(0).getRequest();
            idTravel = messageEntityObservableList.get(0).getId_travel_M();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    @FXML
    public void deleteMessageButton(ActionEvent actionEvent) {
        messagesService.deleteMessage(messageID);
    }

    @FXML
    public void clickTakeIdForDelete(MouseEvent mouseEvent) {
        ObservableList<TravelEntity> travelEntityObservableList;
        travelEntityObservableList = travelTable.getSelectionModel().getSelectedItems();

        idTravelForDelete = travelEntityObservableList.get(0).getId_travel();
    }

    @FXML
    public void buttonCancelTravel(ActionEvent actionEvent) {
        if(travelService.deleteTravel(idTravelForDelete)){
            cancelledMessageLabel.setTextFill(Color.GREEN);
            cancelledMessageLabel.setText("Successfully cancelled travel: ");
            SendingNotifications.sendingNotifications(NotificationsPath.DISTRIBUTOR_CANCEL_TRAVEL_FILE.getPath(), idTravelForDelete+" id travel is cancelled");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event2 -> {
                cancelledMessageLabel.setText("");
            });
            delay.play();

        }
    }


    private void setTexts(){
        welcomeLabel.setText("Welcome, " + "\n"+ CurrentUser.getUser().getUserName());

        ObservableList<String> typeOfTravelList = FXCollections.observableArrayList(TypeOfTravel.ONE_WAY.getType(),TypeOfTravel.TWO_WAY.getType());
        comboBoxTypeOfTravel.setItems(typeOfTravelList);

        ObservableList<String> destinationList = FXCollections.observableArrayList(Destination.VARNA.getDestination(),
                Destination.SOFIA.getDestination(),Destination.SHUMEN.getDestination(),Destination.DOBRICH.getDestination());

        comboBoxStartingStation.setItems(destinationList);
        comboBoxTerminalStation.setItems(destinationList);

        ObservableList<String> modeOfTransportList = FXCollections.observableArrayList(ModeOfTransport.BUS.getMode(),ModeOfTransport.TRAIN.getMode());
        comboBoxModeOfTransport.setItems(modeOfTransportList);

        File logoutFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);

        File busFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\bus.png");
        Image busImage = new Image(busFile.toURI().toString());
        busIconImageView.setImage(busImage);

        File refreshFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

        File travelFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\travelcompany.png");
        Image travelImage = new Image(travelFile.toURI().toString());
        travelCompanyImageView.setImage(travelImage);

        File travelIconFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\travelcompany.png");
        Image travelIconImage = new Image(travelIconFile.toURI().toString());
        travelImageView.setImage(travelIconImage);

        File dashboardFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\dashboard.png");
        Image dashboardImage = new Image(dashboardFile.toURI().toString());
        dashboardImageView.setImage(dashboardImage);

        File messageFile = new File("C:\\Users\\USER\\IdeaProjects\\PublicTransport\\PublicTransport\\Images\\meassages.png");
        Image messageImage = new Image(messageFile.toURI().toString());
        messageImageView.setImage(messageImage);
    }

    private boolean isValidFields() {
        return !comboBoxModeOfTransport.getItems().isEmpty() && !comboBoxStartingStation.getItems().isEmpty() && !comboBoxTerminalStation.getItems().isEmpty() &&
                !comboBoxTypeOfTravel.getItems().isEmpty() && !ticketLimitTextField.getText().isEmpty()&&
                !numberOfPlacesTextField.getText().isEmpty() && !dateOfDepartureDatePicker.getEditor().getText().isEmpty()
                && !dateOfArrivalDatePicker.getEditor().getText().isEmpty() && !priceTicketTextField.getText().isEmpty();
    }

    private boolean isCreated(){
        return travelService.addTravel(typeOfTravel,startingStation,terminalStation,
                Date.valueOf(dateOfDepartureDatePicker.getValue()).toLocalDate(),
                Date.valueOf(dateOfArrivalDatePicker.getValue()).toLocalDate(),
                Integer.parseInt(numberOfPlacesTextField.getText()),modeOfTransport,
                Integer.parseInt(ticketLimitTextField.getText()),
                (TravelCompanyEntity) CurrentUser.getUser(),Double.parseDouble(priceTicketTextField.getText()));


    }

    private void exit(){
        buttonExit.setOnAction(SceneController::close);
    }

    private void fillTravelTableWithData(){
        typeOfTravelTC.setCellValueFactory(new PropertyValueFactory<>("typeOfTravel"));
        startingStationTC.setCellValueFactory(new PropertyValueFactory<>("startingStation"));
        dateOfDepartureTC.setCellValueFactory(new PropertyValueFactory<>("dateOfDeparture"));
        dateOfArrivalTC.setCellValueFactory(new PropertyValueFactory<>("dateOfArrival"));
        numberOfPlacesTC.setCellValueFactory(new PropertyValueFactory<>("numberOfPlaces"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        ticketLimitTC.setCellValueFactory(new PropertyValueFactory<>("ticketLimit"));
        idTC.setCellValueFactory(new PropertyValueFactory<>("id_travel"));
        cashierTC.setCellValueFactory(new PropertyValueFactory<>("id_cashier_T"));

        travelTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        travelTable.setItems(getTravelObservableList());
    }

    private ObservableList<TravelEntity> getTravelObservableList(){
        ObservableList<TravelEntity> travels = FXCollections.observableArrayList();
        travels.addAll(travelService.getTravelForTC(((TravelCompanyEntity) CurrentUser.getUser()).getTc_id()));
        return travels;
    }

    private boolean isValidTicketLimit(){
        int numberOfPlaces = Integer.parseInt(numberOfPlacesTextField.getText());
        int ticketLimit = Integer.parseInt(ticketLimitTextField.getText());
        if(ticketLimit <= 0 && ticketLimit > numberOfPlaces){
            return false;
        }
        return true;
    }

    private void fillMessageTableWithData(){
        idMessage.setCellValueFactory(new PropertyValueFactory<>("id_message"));
        requestMessage.setCellValueFactory(new PropertyValueFactory<>("request"));
        answerMessage.setCellValueFactory(new PropertyValueFactory<>("answer"));
        idTravelMessage.setCellValueFactory(new PropertyValueFactory<>("id_travel_M"));

        messageTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        messageTable.setItems(getMessageObservableList());

    }

    private ObservableList<MessagesEntity> getMessageObservableList(){
        ObservableList<MessagesEntity> messages = FXCollections.observableArrayList();
        messages.addAll(messagesService.getMessages(((TravelCompanyEntity) CurrentUser.getUser()).getTc_id()));
        return messages;
    }



}
