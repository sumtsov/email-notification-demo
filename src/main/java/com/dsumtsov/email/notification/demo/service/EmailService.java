package com.dsumtsov.email.notification.demo.service;

import com.dsumtsov.email.notification.demo.domain.Document;

public interface EmailService {

    void sendMessageWithDocument(Document document);
}
