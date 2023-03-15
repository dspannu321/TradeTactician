//13-mar-2023
package com.tradetactician.application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.tradetactician.application.dependencies.ApplicationData;
import com.tradetactician.application.dependencies.DatabaseConnection;
import com.tradetactician.application.dependencies.Hashing;
import com.tradetactician.application.dependencies.ViewChanger;
import com.tradetactician.application.logic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.PreparedStatement;

public class Register {
    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField phoneNo;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField userId;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField repeatPassword;

    @FXML
    private JFXPasswordField otp;

    @FXML
    private JFXButton generateOTP;

    @FXML
    private JFXButton submit;

    @FXML
    void generateOTP(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

        //verify if form is filled
        if (isRegisterFormFilled()){
            //verify if passwords match
            if (arePasswordsMatching()){
                //verify if user existing already
                if (isNotExistingUser()){
                    if (addUserToDatabase()){
                        ApplicationData.loadUsers();
                    }
                }
                else {
                    System.out.println("User Already Exists.");
                }
            }
            else {
                System.out.println("Passwords doesn't Match.");
            }
        }
        else {
            System.out.println("Fields cannot be empty.");
        }

    }

    @FXML
    void signIn(ActionEvent actionEvent) {
        try{
            ViewChanger.changeViewForward(actionEvent,"login.fxml","Login - TradeTactician");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //these functions are required for background functionality of the register module

    private boolean isRegisterFormFilled(){
        return !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !phoneNo.getText().isEmpty() && !email.getText().isEmpty() && !userId.getText().isEmpty() && !password.getText().isEmpty() && !repeatPassword.getText().isEmpty() && !otp.getText().isEmpty();
    }
    private boolean isNotExistingUser(){
        boolean isNotExistingUser = true;
        try{

            for (User user: ApplicationData.getUser()){
                if (user.getUserId().equalsIgnoreCase(userId.getText())){
                    isNotExistingUser = false;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return isNotExistingUser;
    }
    private boolean arePasswordsMatching(){
        return password.getText().equals(repeatPassword.getText());
    }

    private boolean addUserToDatabase(){
        try{
            Thread addUserToDatabase = new Thread(()->{
                try{
                    String sql = "INSERT INTO user VALUES(?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = DatabaseConnection.getConnection().establishDatabaseConnection(sql);
                    preparedStatement.setString(1,userId.getText());
                    preparedStatement.setString(2,firstName.getText());
                    preparedStatement.setString(3,lastName.getText());
                    preparedStatement.setString(4,phoneNo.getText());
                    preparedStatement.setString(5,email.getText());
                    preparedStatement.setString(6,hashedPassword(password.getText()));

                    int rowAdded = preparedStatement.executeUpdate();
                    if (rowAdded == 1){
                        System.out.println("User Created Successfully");
                    }
                    else {
                        System.out.println("Something went Wrong");
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                });
            addUserToDatabase.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private String hashedPassword(String plainTextPassword){
        String salt = Hashing.generateSalt(1024);
        assert salt != null;
        String hashedPassword = Hashing.hashPassword(plainTextPassword,salt);
        return salt+hashedPassword;
    }
}
