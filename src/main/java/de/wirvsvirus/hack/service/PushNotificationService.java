package de.wirvsvirus.hack.service;

public interface PushNotificationService {
    String getSenderId();

    void sendMessage(String receiptId, String title, String body);
}
