package de.wirvsvirus.hack.service;

import de.wirvsvirus.hack.exception.PushMessageNotSendException;

public interface PushNotificationService {
    String getSenderId();

    void sendMessage(String receiptId, String title, String body) throws PushMessageNotSendException;
}
