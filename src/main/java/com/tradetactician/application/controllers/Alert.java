package com.tradetactician.application.controllers;

import com.jfoenix.controls.JFXButton;
import com.tradetactician.application.dependencies.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Alert implements Initializable {

    @FXML
    private Label message;
    @FXML private JFXButton okButton;
    private String userConfirmation;
    @FXML private Label messageToConfirm;

    @FXML private JFXButton confirmYesButton;



    @FXML private void okButton(ActionEvent actionEvent){
        ViewChanger.closeView(actionEvent);
    }
    public void setMessage(String message){
        this.message.setText(message);
    }
    @FXML private void confirmYes(ActionEvent actionEvent){
        ViewChanger.closeView(actionEvent);
        setUserConfirmation("yes");
    }
    @FXML private void confirmNo(ActionEvent actionEvent){
        ViewChanger.closeView(actionEvent);
        setUserConfirmation("no");
    }
    public void setMessageToConfirm(String messageToConfirm){
        this.messageToConfirm.setText(messageToConfirm);
    }
    public String getUserConfirmation(){
        return userConfirmation;
    }
    private void setUserConfirmation(String userConfirmation){
        this.userConfirmation=userConfirmation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            okButton.setDefaultButton(true);

        }
        catch (Exception ignored){

        }

    }
}
