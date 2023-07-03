package com.drzewek.wfrp_npc_generator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private ResourceBundle applicationMessages = ResourceBundle.getBundle("ApplicationMessages");

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendVerificationEmail(String username, String to, String token, String language) {
        String body = applicationMessages.getString("email.verification.message." + language);
        body = String.format(body, username, token);
        String subject = applicationMessages.getString("email.verification.subject." + language);
        sendEmail(to, subject, body);
    }
}