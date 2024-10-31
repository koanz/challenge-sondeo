package com.sondeos.javanotifychallenge.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidationRules {

    public Boolean validateSmsMessage(String message) {
        return message.length() <= 160;
    }

    public Boolean validateEmailFormat(String email) {
        if(email.equals(""))
            return false;

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public Boolean validatePhoneNumberFormat(String phoneNumber) {
        if(phoneNumber.equals(""))
            return false;

        return phoneNumber.length() >= 10 && phoneNumber.length() <= 13;
    }

}
