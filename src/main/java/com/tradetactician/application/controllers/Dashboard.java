package com.tradetactician.application.controllers;

import com.tradetactician.application.dependencies.ApplicationData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status.setText(ApplicationData.getUserMapToUserId(Login.getLoggedInUser()).getFirstname()+" "+ApplicationData.getUserMapToUserId(Login.getLoggedInUser()).getLastname());
    }
}
