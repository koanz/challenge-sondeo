package com.sondeos.javanotifychallenge.controllers;

import com.sondeos.javanotifychallenge.services.NotifyService;
import com.sondeos.javanotifychallenge.services.NotifyServiceAsync;
import com.sondeos.javanotifychallenge.services.dto.NotificationProcessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {

    @Autowired
    NotifyService notifyService;


    @GetMapping("/")
    public String home() {
        return "Java Notify Challenge - Welcome!";
    }


    @GetMapping("/process")
    public String process() {

        NotificationProcessResult r = notifyService.processNotifications();

        return "{ \"processed\": " + r.getProcessed() + ",  \"sent\": " + r.getSent() + ", \"duration\": " + r.getDuration() + "}";

    }

}
