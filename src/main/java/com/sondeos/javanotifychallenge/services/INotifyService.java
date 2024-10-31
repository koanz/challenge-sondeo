package com.sondeos.javanotifychallenge.services;

public interface INotifyService {
    public Boolean notifyByEmail(String email, String message);

    public Boolean notifyBySms(String phoneNumber, String message);

}
