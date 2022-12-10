package com.example.JFX.Controller;

import com.example.Helpers.CurrentUser;
import com.example.Helpers.Log4j;
import com.example.Helpers.Travels.Status;
import com.example.HibernateOracle.DAO.TravelDAO;
import com.example.HibernateOracle.Model.CashierEntity;
import com.example.HibernateOracle.Model.TravelEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CashierHomeController implements Initializable {
    @FXML
    public ImageView logoutImageView;
    @FXML
    public Button buttonLogout;
    @FXML
    public ImageView cashierImageView;
    @FXML
    public Button refreshButton;
    @FXML
    public ImageView refreshImageView;
    @FXML
    public Button dashboardButton;
    @FXML
    public ImageView dashboardImageView;
    @FXML
    public Button buttonExit;
    @FXML
    public Label titleLabel;
    @FXML
    public ImageView busIconImageView;
    @FXML
    public Button addTicketsButton;
    @FXML
    public ImageView ticketsImageView;
    @FXML
    public TableView<TravelEntity> editTable;
    @FXML
    public TableColumn<TravelEntity,String> typeOfTravelColumn;
    @FXML
    public TableColumn <TravelEntity,String> startingStationColumn;
    @FXML
    public TableColumn<TravelEntity,String> terminalStationColumn;
    @FXML
    public TableColumn<TravelEntity,Long>  ticketsColumn;
    @FXML
    public TableColumn <TravelEntity, LocalDate> dateOfDepartureColumn;
    @FXML
    public TableColumn<TravelEntity,LocalDate>  dateOfArrivalColumn;
    @FXML
    public Button insertButton;
    @FXML
    public TableColumn<TravelEntity,String> modeOfTransportColumn;
    @FXML
    public AnchorPane editCashierAnchorPane;
    @FXML
    public Label messageLabel;
    @FXML
    public AnchorPane dashboardAnchorPane;

    private final TravelDAO travelDAO = new TravelDAO();



    private int idCashierForCashier;
    private int idTravelForCashier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCashierTableWithData();
        setTexts();
        exit();
    }

    @FXML
    public void buttonLogout(ActionEvent actionEvent) throws IOException {
        Log4j.logger.warn("User named:" + CurrentUser.getUser().getUserName() + " has logged out ");
        SceneController.getLoginScene(actionEvent);
    }

    @FXML
    public void refreshButton(ActionEvent actionEvent) throws IOException {
        SceneController.getCashierHomeScene(actionEvent);
    }

    @FXML
    public void dashboardButton(ActionEvent actionEvent) {
        editCashierAnchorPane.setVisible(false);
        dashboardAnchorPane.setVisible(true);
    }

    @FXML
    public void addTicketsButton(ActionEvent actionEvent) throws IOException {
        dashboardAnchorPane.setVisible(false);
        editCashierAnchorPane.setVisible(true);
    }

    @FXML
    public void selectTravel(MouseEvent mouseEvent) {
        ObservableList<TravelEntity> travelEntityObservableList;
        travelEntityObservableList = editTable.getSelectionModel().getSelectedItems();

        idCashierForCashier = travelEntityObservableList.get(0).getId_cashier_T();
        idTravelForCashier = travelEntityObservableList.get(0).getId_travel();


    }

    @FXML
    public void insertButton(ActionEvent actionEvent) {
        if(travelDAO.updateStatus(Status.TRUE.getStatus(), idTravelForCashier)){
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Successfully added");
            return;
        }

        messageLabel.setTextFill(Color.RED);
        messageLabel.setText("Error while adding");
    }

    private void setTexts() {
        File logoutFile = new File("Images/Logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutImageView.setImage(logoutImage);

        File dashboardFile = new File("Images/dashboard.png");
        Image dashboardImage = new Image(dashboardFile.toURI().toString());
        dashboardImageView.setImage(dashboardImage);

        File cashierFile = new File("Images/cashierIcon.png");
        Image cashierImage = new Image(cashierFile.toURI().toString());
        cashierImageView.setImage(cashierImage);

        File refreshFile = new File("Images/refresh.png");
        Image refreshImage = new Image(refreshFile.toURI().toString());
        refreshImageView.setImage(refreshImage);

        File ticketFile = new File("Images/plane-tickets.png");
        Image ticketImage = new Image(ticketFile.toURI().toString());
        ticketsImageView.setImage(ticketImage);

        File busFile = new File("Images/bus.png");
        Image busImage = new Image(busFile.toURI().toString());
        busIconImageView.setImage(busImage);

    }
    private void fillCashierTableWithData(){
        typeOfTravelColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfTravel"));
        startingStationColumn.setCellValueFactory(new PropertyValueFactory<>("startingStation"));
        terminalStationColumn.setCellValueFactory(new PropertyValueFactory<>("terminalStation"));
        dateOfDepartureColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfDeparture"));
        dateOfArrivalColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfArrival"));
        modeOfTransportColumn.setCellValueFactory(new PropertyValueFactory<>("modeOfTransport"));
        ticketsColumn.setCellValueFactory(new PropertyValueFactory<>("ticketLimit"));


        editTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        editTable.setItems(getTravelObservableList());
    }

    private ObservableList<TravelEntity> getTravelObservableList(){
        ObservableList<TravelEntity> travels = FXCollections.observableArrayList();
        travels.addAll(travelDAO.getTravelsForCashier(((CashierEntity) CurrentUser.getUser()).getCashier_id(), Status.FALSE.getStatus()));
        return travels;
    }
    private void exit() {
        buttonExit.setOnAction(SceneController::close);
    }


}

