//12-mar-2023
package com.tradetactician.application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.tradetactician.application.dependencies.ApplicationData;
import com.tradetactician.application.dependencies.Hashing;
import com.tradetactician.application.dependencies.ViewChanger;
import com.tradetactician.application.logic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML private JFXTextField userId;//Text field to take UserID
    @FXML private JFXPasswordField password;//Password field to take user password
    @FXML private JFXButton login;// login button //this is declared to make it default button, so it can be bound to enter key

    private static String loggedInUser;// this will hold user id of user logged in the system.

    //method to initiate login. this will run when login button will be clicked
    @FXML private void login(ActionEvent actionEvent){
        //verify form is filled
        if (loginFormIsFilled()){
            //authenticate user
            if (isAuthenticUser(userId.getText(),password.getText())){
                try{
                    Login.setLoggedInUser(userId.getText());
                    ViewChanger.changeViewForward(actionEvent,"dashboard.fxml","Dashboard - TradeTactician");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Invalid Credentials. Try Again.");
                clear();// this clears the form so user can try to log in again
            }

        }
        else {
            System.out.println("UserID and password cannot be empty.");
        }

    }
    //method to clear login form. this will run when clear button will be clicked
    @FXML private void clear(){
        try{
            userId.clear();
            password.clear();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //this will run when forgot password button will be clicked
    @FXML private void forgotPassword(){}
    //method to register. this will run when register will be clicked
    @FXML private void register(ActionEvent actionEvent){
        try{
            ViewChanger.changeViewForward(actionEvent,"register.fxml","Registration - TradeTactician");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //These methods are required for background functionality of this component(Login)
    private boolean loginFormIsFilled(){
        return !userId.getText().isEmpty() && !password.getText().isEmpty();
    }
    private boolean isAuthenticUser(String userId,String password){
        String hashedPassword=null;
        boolean userIsAuthenticated=false;
        try{
            hashedPassword= ApplicationData.getUserMapToUserId(userId).getPassword();
            String key=hashedPassword.substring(1368);
            String salt=hashedPassword.substring(0,1368);
            userIsAuthenticated= Hashing.verifyPassword(password,key,salt);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return userIsAuthenticated;    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(String loggedInUser) {
        Login.loggedInUser = loggedInUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setDefaultButton(true);//this binds the login button with enter key
    }


}
