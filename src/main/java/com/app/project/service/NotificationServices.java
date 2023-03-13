package com.app.project.service;

import com.app.project.interfaces.INotificationService;

public class NotificationServices implements INotificationService {
    @Override
    public void sendMessage(String message, String recipientIdentifier) {
        System.out.println("The" + message+ " has been sent to :" + recipientIdentifier);
    }
}
