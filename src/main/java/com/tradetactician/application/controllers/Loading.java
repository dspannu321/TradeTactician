package com.tradetactician.application.controllers;

import com.jfoenix.controls.JFXButton;
import com.tradetactician.application.dependencies.ApplicationData;
import com.tradetactician.application.dependencies.ViewChanger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Loading implements Initializable {

    @FXML
    JFXButton actionButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Thread loadUsers = new Thread(()->{
                try{
                   if (ApplicationData.loadUsers()){
                       Platform.runLater(()->{
                           actionButton.fire();
                       });
                   }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            loadUsers.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void actionButton(ActionEvent actionEvent){
        try{
            ViewChanger.changeViewForward(actionEvent,"login.fxml","Login - TradeTactician");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
