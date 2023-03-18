package com.tradetactician.application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.tradetactician.application.dependencies.*;
import com.tradetactician.application.dependencies.Alert;
import com.tradetactician.application.logic.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ForgotPassword implements Initializable {

    @FXML
    private Label userId;

    @FXML private JFXButton notUser;

    @FXML
    private JFXButton phoneOTP;

    @FXML
    private JFXButton emailOTP;

    @FXML
    private JFXPasswordField otp;

    @FXML
    private JFXButton verifyOTP;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField repeatPassword;

    @FXML
    private JFXButton submit;

    private static String oneTimePassword;

    @FXML private JFXSpinner submitSpinner;

    @FXML void notUser(ActionEvent actionEvent){
        try{
            ViewChanger.changeViewBackward(actionEvent,"login.fxml","TradeTactician - Login");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    @FXML
    void emailOTP(ActionEvent event) {
        //send otp via email
        Thread thread = new Thread(()->{
            setOneTimePassword(Email.requestVerification(ApplicationData.getUserMapToUserId(userId.getText()).getEmail()));
            Platform.runLater(()->{
                otp.setVisible(true);
                verifyOTP.setVisible(true);
                phoneOTP.setDisable(true);
                emailOTP.setDisable(true);
            });
        });
        thread.start();


    }

    @FXML
    void phoneOTP(ActionEvent event) {
        //send otp via phone
        Thread thread = new Thread(()->{
            setOneTimePassword(MessageSender.requestVerification(ApplicationData.getUserMapToUserId(userId.getText()).getPhoneNo()));
            Platform.runLater(()->{
                otp.setVisible(true);
                verifyOTP.setVisible(true);
                phoneOTP.setDisable(true);
                emailOTP.setDisable(true);
            });
        });
        thread.start();

    }

    @FXML
    void submit(ActionEvent actionEvent) {
        if (!password.getText().isEmpty() && !repeatPassword.getText().isEmpty()){
            if (ifPasswordsMatch()){
                submitSpinner.setVisible(true);
                submit.setDisable(true);
                Thread thread = new Thread(()->{
                    try {
                        String sql = "UPDATE user SET password=(?) WHERE userId =(?)";
                        PreparedStatement preparedStatement = DatabaseConnection.getConnection().establishDatabaseConnection(sql);
                        preparedStatement.setString(1,hashedPassword(password.getText()));
                        preparedStatement.setString(2,userId.getText());
                        int updatedRow = preparedStatement.executeUpdate();
                        if (updatedRow == 1){
                            System.out.println("Password Updated Successfully. You can login with new password.");
                            Platform.runLater(ApplicationData::loadUsers);
                            Platform.runLater(()->{
                                Alert.showSuccess("Password Updated Successfully. You can login with new password.");
                                ViewChanger.changeViewBackward(actionEvent,"login.fxml","TradeTactician - Login");
                            });
                        }
                        else {
                            System.out.println("Something Went Wrong.");
                            Platform.runLater(()->{
                                Alert.showWarning("Something Went Wrong.");
                                submitSpinner.setVisible(false);
                                submit.setDisable(false);
                            });

                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
            else {
                System.out.println("Passwords doesn't Match. Try Again.");
                Alert.showWarning("Passwords doesn't Match. Try Again.");

            }
        }
        else {
            System.out.println("Both Password Fields are Required.");
            Alert.showMessage("Both Password Fields are Required.");
        }
    }

    @FXML
    void verifyOTP(ActionEvent event) {
        try{
            if (otp.getText().equals(getOneTimePassword())){
                password.setVisible(true);
                repeatPassword.setVisible(true);
                submit.setVisible(true);
                phoneOTP.setDisable(true);
                emailOTP.setDisable(true);
                verifyOTP.setDisable(true);
            }
            else{
                System.out.println("Invalid OTP. Try Again.");
                Alert.showWarning("Invalid OTP. Try Again.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getOneTimePassword() {
        return oneTimePassword;
    }

    public static void setOneTimePassword(String oneTimePassword) {
        ForgotPassword.oneTimePassword = oneTimePassword;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(String userId){
        this.userId.setText(userId);
        notUser.setText("not "+userId+"?");
        emailOTP.setText("Request OTP on Email");
        phoneOTP.setText("Request OTP on Phone");
    }
    private boolean ifPasswordsMatch(){
        return password.getText().equals(repeatPassword.getText());
    }
    private String hashedPassword(String plainTextPassword){
        String salt = Hashing.generateSalt(1024);
        assert salt != null;
        String hashedPassword = Hashing.hashPassword(plainTextPassword,salt);
        return salt+hashedPassword;
    }


}
