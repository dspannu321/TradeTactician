package com.tradetactician.application.dependencies;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class Alert {



    public static void showWarning(String warningMessage){
        try{
            ViewChanger.openNewViewWithData("alertWarning.fxml","setMessage",warningMessage,"Warning");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void showMessage(String message){
        try{
            ViewChanger.openNewViewWithData("alertMessage.fxml","setMessage",message,"Message");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void showProgress(String progress){
        try{
            ViewChanger.openNewViewWithData("alertProgress.fxml","setProgress",progress,"Please Wait");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void showSuccess(String successMessage){
        try{
            ViewChanger.openNewViewWithData("alertSuccess.fxml","setMessage",successMessage,"Message");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getConfirmation(String messageToConfirm){
        String userConfirmation=null;
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(Alert.class.getResource("/com/tradetactician/application/views/alertConfirmation.fxml"));
            Parent parent=loader.load();
            Scene displayConfirmationBox=new Scene(parent);
            com.tradetactician.application.controllers.Alert controller=loader.getController();
            controller.setMessageToConfirm(messageToConfirm);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(displayConfirmationBox);
            displayConfirmationBox.setFill(Color.TRANSPARENT);
            Image icon = new Image(Objects.requireNonNull(Alert.class.getResourceAsStream("/com/tradetactician/application/graphics/images/bull-background.jpg")));
            stage.getIcons().add(icon);
            stage.setTitle("Confirmation");
            stage.showAndWait();
            userConfirmation=controller.getUserConfirmation();
        }
        catch (Exception e){
            e.printStackTrace();
        }
       return userConfirmation;
    }

}
