package com.tradetactician.application.dependencies;
//this class holds all the credentials required for TradeTactician applications
//17/Mar/2023
public class CredentialsManager {

    //---------------------------------------------------------//these credentials are for database connection
    public static String getDatabaseURl() {

        return "databaseURL";
    }

    public static String getDatabaseUserName() {
        return "databaseUserName";
    }

    public static String getDatabasePassword() {
        return "databasePassword";
    }

    //------------------------------------------------------------//these credential are for vonage api used in MessageSender class
    public static String getVonageApiKey() {

        return "vonageApiKey";
    }

    public static String getVonageApiSecret() {
        return "vonageApiSecret";
    }

    public static String getVonageApiPhoneNo() {
        return "vonageApiPhoneNo";
    }

    //------------------------------------------------------------- //these credentials are for java mail used in Email
    public static String getEmailUserName() {

        return "emailUserName";
    }

    public static String getEmailPassword() {
        return "emailPassword";
    }

    public static String getEmailHostName() {
        return "emailHostName";
    }

    public static int getEmailPortNumber() {
        return 000;
    }
}
