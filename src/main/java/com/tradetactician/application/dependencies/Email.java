package com.tradetactician.application.dependencies;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;


public class Email {
    private static final String username = "user_name_email_address";
    private static final String password = "password";
    private static final String host = "host_server";
    private static final int port = 000;
    private static String verificationCode;



    public static void sendEmail(String receiver,String subject,String body) throws MessagingException {
        try{

            System.out.println("SSLEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", host); //SMTP Host
            props.put("mail.smtp.socketFactory.port", port); //SSL Port
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
            props.put("mail.smtp.port", port); //SMTP Port

            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getDefaultInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.setFrom(new InternetAddress(username, "TradeTactician Admin"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body,"UTF-8","html");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getVerificationCode() {
        return verificationCode;
    }

    public static void setVerificationCode(String verificationCode) {
        Email.verificationCode = verificationCode;
    }

    public static String requestVerification(String emailId){
        try {
            setVerificationCode(generateVerificationCode());
            Email.sendEmail(emailId, "Verification Code - Trade Tactician", "Your One time password for Tradetactician Application is " + getVerificationCode() + ". Do not Share this code with anyone.");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return getVerificationCode();
    }

    private static String generateVerificationCode(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<6;i++){
            builder.append(ThreadLocalRandom.current().nextInt(0, 9 + 1));
        }
        return builder.toString();
    }
}

