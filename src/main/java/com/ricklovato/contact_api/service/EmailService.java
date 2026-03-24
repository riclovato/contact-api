package com.ricklovato.contact_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ricklovato.contact_api.dto.EmailDTO;

@Service
public class EmailService {

    private final RestTemplate restTemplate;
    @Value("${mail.to}")
    private String mailTo;
    @Value("${mail.subject}")
    private String mailSubject;
    @Value("${brevo.api.key}")
    private String apiKey;

    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(EmailDTO emailDTO) {

       
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey); 
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("sender", Map.of("email", emailDTO.email()));
        body.put("to", List.of(Map.of("email", mailTo))); 
        body.put("subject", mailSubject);
        body.put("textContent", "De: " + emailDTO.email() + "\n\n" + emailDTO.message());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity("https://api.brevo.com/v3/smtp/email", request, String.class);
    }
}
