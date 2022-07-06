package com.dsumtsov.email.notification.demo.service.impl;

import com.dsumtsov.email.notification.demo.config.properties.EmailProperties;
import com.dsumtsov.email.notification.demo.domain.Document;
import com.dsumtsov.email.notification.demo.service.EmailService;
import com.dsumtsov.email.notification.demo.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailProperties properties;
    private final JavaMailSender sender;

    @Override
    public void sendMessageWithDocument(Document document) {
        if (Objects.isNull(document)) {
            log.info("No document provided, message not sent");
            return;
        }

        try {
            var message = createMessage(document);
            sender.send(message);
            log.info("Message sent successfully");
        } catch (Exception e) {
            log.error("Failed to send message: {}", e.getMessage());
        }
    }

    private MimeMessage createMessage(Document document) throws MessagingException {
        var message = sender.createMimeMessage();
        message.setFrom(properties.getFrom());
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(String.join(",", properties.getRecipients())));
        message.setSubject(String.format("%s report", DateUtils.getCurrentDateFormatted()));
        message.setSentDate(new Date());
        message.setText("New notification");
        message.setContent(createContent(document));

        return message;
    }

    private Multipart createContent(Document document) throws MessagingException {
        var multipart = new MimeMultipart();
        addAttachment(multipart, document.getName(), document.getBody());

        return multipart;
    }

    private void addAttachment(Multipart multipart,
                               String fileName,
                               byte[] report) throws MessagingException {
        var bodyPart = new MimeBodyPart();
        var dataSource = new ByteArrayDataSource(report, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        bodyPart.setDataHandler(new DataHandler(dataSource));
        bodyPart.setFileName(fileName);
        multipart.addBodyPart(bodyPart);
    }
}
