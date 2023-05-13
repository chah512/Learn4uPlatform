package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class SmsTwillio {

    public static final String ACCOUNT_SID = "AC0dd5c9e809a5fdd822502f4c7fde9f87";
    public static final String AUTH_TOKEN = "3987d7473f70aa63c9309cb6bc696f16";
    public static void sms(String s) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("++21626921663"),
                        new com.twilio.type.PhoneNumber("+12395108502"),
                        " A formation has been aded to your agency : "+s).create();
        System.out.println(message.getSid());
    }
     public static void smsCancelRent(String s) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("++21626921663"),
                new com.twilio.type.PhoneNumber("+12395108502"),
                " your formation : "+s+" has been deleted ").create();
        System.out.println(message.getSid());
    }

    public static void smsRent(String s,String s1) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("++21692294022"),
                new com.twilio.type.PhoneNumber("+18624659528"),
                " your rent from "+s+" to "+s1+" has been done ").create();
        System.out.println(message.getSid());
    }
}
