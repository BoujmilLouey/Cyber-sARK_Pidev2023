/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;

/**
 *
 * @author BAZINFO
 */
public class SMSNotifier {
     public static final String ACCOUNT_SID = "ACb8752af82006d3e7939e58bf557787d0";
    public static final String AUTH_TOKEN = "d5b87252fbfa8c6d14e176bd83553791";
  //  public static final String TWILIO_NUMBER = "+16205434078";

    public static void sendSms(String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+21699027754"),new com.twilio.type.PhoneNumber("+13155993597"),body).create();
        System.out.println(message.getSid());
    }
    
}
