package com.ricklovato.contact_api.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record  EmailDTO(
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required") String email, 
    @NotBlank(message = "Message is required") String message) {}
