package com.sondeos.javanotifychallenge.services.impl;

import com.sondeos.javanotifychallenge.providers.EmailProvider;
import com.sondeos.javanotifychallenge.providers.SmsProvider;
import com.sondeos.javanotifychallenge.services.INotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class NotifyServiceImpl implements INotifyService {
    @Autowired
    private EmailProvider emailProvider;

    @Autowired
    private SmsProvider smsProvider;

    @Override
    //@CachePut("emails")
    public Boolean notifyByEmail(String destination, String message) {
        // Procesar email

        try {
            return emailProvider.notify(destination, message).getStatus().equalsIgnoreCase("sent");
        } catch (HttpServerErrorException | HttpClientErrorException ex) {
            // Tratar el error
            return false;
        }
    }

    @Override
    //@CachePut("sms")
    public Boolean notifyBySms(String destination, String message) {
        // Procesar sms

        try {
            return smsProvider.notify(destination, message).getStatus().equalsIgnoreCase("sent");
        } catch (HttpServerErrorException | HttpClientErrorException ex) {
            // Tratar el error
            return false;
        }
    }

}
