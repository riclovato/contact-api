package com.ricklovato.contact_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ricklovato.contact_api.dto.EmailDTO;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    @Value("${mail.to}")
    private String mailTo;
    @Value("${mail.subject}")
    private String mailSubject;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailTo);
        message.setTo(mailTo);
        message.setSubject(mailSubject);
        message.setText("de: " + emailDTO.email() + "\n\n" + emailDTO.message());
        mailSender.send(message);
    }
}
