package com.tradetactician.application.dependencies;

import javafx.application.Platform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

//this class will help program to connect with database
//March 12, 2023
public class DatabaseConnection {


    private static final String dbDriverClass="com.mysql.jdbc.Driver";
    private static final String dbURL = CredentialsManager.getDatabaseURl();
    private static final String userName = CredentialsManager.getDatabaseUserName();
    private static final String password = CredentialsManager.getDatabasePassword();
    private static boolean isConnectionActive=true;

    public static boolean isIsConnectionActive() {
        return isConnectionActive;
    }

    public static void setIsConnectionActive(boolean isConnectionActive) {
        DatabaseConnection.isConnectionActive = isConnectionActive;
    }

    public static Statement establishDatabaseConnection() throws IOException {
        Statement statement=null;
        try {
            //Class.forName(dbDriverClass);
            Connection connection= DriverManager.getConnection(dbURL,userName,password);
            statement=connection.createStatement();
        }
        catch(Exception e) {
            Platform.runLater(()->{
                try{
                    DatabaseConnection.setIsConnectionActive(false);
                    Alert.showWarning("Unable to Communicate with Database. Please check your Internet connection.");

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            });

            e.printStackTrace();
        }
        return statement;
    }
    public static DatabaseConnection getConnection(){
        return new DatabaseConnection();
    }
    public PreparedStatement establishDatabaseConnection(String sql) throws IOException {
        PreparedStatement statement=null;
        try {
            Class.forName(dbDriverClass);
            Connection connection=DriverManager.getConnection(dbURL,userName,password);
            statement=connection.prepareStatement(sql);
        }
        catch(Exception e) {
            Alert.showWarning("Error occurred while creating connection with Database Server.");
            e.printStackTrace();
        }
        return statement;
    }

}
