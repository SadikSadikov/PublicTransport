package com.example.JFX.Controller;

import com.example.Helpers.IconPath;
import com.example.Helpers.ScenePath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class SceneController {

    private static double x;
    private static double y;

    private static Parent main;

    public static void getInitialScene(Stage stage) throws IOException {
        main = FXMLLoader.load(SceneController.class.getResource(ScenePath.LOGIN.getPath()));
        Scene scene = new Scene(main);
        controlDrag(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(IconPath.MAIN.getPath()));
        stage.setScene(scene);
        stage.show();
    }

    public static void getRegisterScene(ActionEvent event) throws IOException {
        changeScreen(event,ScenePath.REGISTER.getPath(), IconPath.MAIN.getPath());
    }

    public static void getLoginScene(ActionEvent event) throws IOException {
        changeScreen(event,ScenePath.LOGIN.getPath(), IconPath.MAIN.getPath());
    }

    public static void getCustomerHomeScene(ActionEvent event) throws IOException {
        changeScreen(event,ScenePath.HOME_CUSTOMER.getPath(), IconPath.MAIN.getPath());
    }

    public static void getAdminHomeScene(ActionEvent event) throws IOException {
        changeScreen(event,ScenePath.HOME_ADMIN.getPath(), IconPath.MAIN.getPath());
    }

    private static void changeScreen(ActionEvent event, String fxmlPath,String iconPath) throws IOException {
        main = FXMLLoader.load(SceneController.class.getResource(fxmlPath));
        Scene visitScene = new Scene(main);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(iconPath));
        controlDrag(window);
        window.setScene(visitScene);
        window.show();
    }


    public static void controlDrag(Stage stage) {
        main.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = stage.getX() - event.getScreenX();
                y = stage.getY() - event.getScreenY();
            }
        });
        main.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + x);
                stage.setY(event.getScreenY() + y);
            }
        });
    }

    public static void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
