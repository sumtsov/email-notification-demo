package com.dsumtsov.email.notification.demo.controller;

import com.dsumtsov.email.notification.demo.domain.Document;
import com.dsumtsov.email.notification.demo.service.EmailService;
import com.dsumtsov.email.notification.demo.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Tag(name = "email notification", description = "Email Notification API")
public class EmailController {

    private final EmailService emailService;
    private final ReportService reportService;

    @PostMapping("/send")
    @Operation(summary = "Send sample email notification")
    public void sendNotification() {
        log.info("Request: send email notification");
        Document document = reportService.getReport();
        emailService.sendMessageWithDocument(document);
    }
}
