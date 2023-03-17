package com.tradetactician.application.dependencies;

import com.tradetactician.application.logic.User;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationData {
    private static final ArrayList<User> user = new ArrayList<>();
    private static final HashMap<String,User> userMapToUserId = new HashMap<>();


    public static ArrayList<User> getUser() {
        return user;
    }

    public static User getUserMapToUserId(String userId) {
        return userMapToUserId.get(userId);
    }
     public static HashMap<String, User> getUserMapToUserId(){
        return userMapToUserId;
     }

    public static boolean loadUsers(){
        try{
            Thread loadUsers = new Thread(()->{
                try {
                    Statement statement = DatabaseConnection.establishDatabaseConnection();
                    String sql = "SELECT * FROM user";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        user.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
                    }
                    for (User user : user){
                        userMapToUserId.put(user.getUserId(),user);
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            loadUsers.start();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
