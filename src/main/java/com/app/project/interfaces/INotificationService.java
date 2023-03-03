package com.app.project.interfaces;

public interface INotificationService {
    /***
     *
     * @param message
     * @param recipientIdentifier , denotes a unique identifier that will be used to identify the recipient
     *                            e.g Phone Number for SMS ,email-id for Email, user IP for push notification
     */
    void sendMessage(String message, String recipientIdentifier);
}
