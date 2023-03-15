package com.tradetactician.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/tradetactician/application/views/loading.fxml")));
        primaryStage.setTitle("Login");
        /*Image icon = new Image(Objects.requireNonNull(Alert.class.getResourceAsStream("logo")));
        primaryStage.getIcons().add(icon);*/
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        System.gc();
    }
}