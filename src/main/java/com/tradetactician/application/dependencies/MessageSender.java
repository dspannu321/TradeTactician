package com.tradetactician.application.dependencies;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import com.vonage.client.verify.CheckResponse;
import com.vonage.client.verify.VerifyResponse;
import com.vonage.client.verify.VerifyStatus;

import java.util.concurrent.ThreadLocalRandom;

public class MessageSender {


    private static final VonageClient client = VonageClient.builder().apiKey(CredentialsManager.getVonageApiKey()).apiSecret(CredentialsManager.getVonageApiSecret()).build();
    private static String verificationCode;

    public static void sendMessage(String messageContent, String phoneNumber) {
        TextMessage message = new TextMessage(CredentialsManager.getVonageApiPhoneNo(), "1"+phoneNumber, messageContent);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }

    public static String getVerificationCode() {
        return verificationCode;
    }

    public static void setVerificationCode(String verificationCode) {
        MessageSender.verificationCode = verificationCode;
    }

    public static String requestVerification(String phoneNumber){
        setVerificationCode(generateVerificationCode());
        MessageSender.sendMessage("Your One time password for Tradetactician Application is "+getVerificationCode()+". Do not Share this code with anyone.",1+phoneNumber);
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
