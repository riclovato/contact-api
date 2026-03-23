package com.ricklovato.contact_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ricklovato.contact_api.service.EmailService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ricklovato.contact_api.dto.EmailDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.validation.Valid;

@CrossOrigin(origins = "https://portfolio-ricardo-lovato.netlify.app")
@RestController
@RequestMapping("api/contato")
public class ContactController {
    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO);
        return ResponseEntity.ok("Email sent successfully");
    }
}
