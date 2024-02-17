package com.example.securityjwt.domain.user;

public record RegisterUserDTO(String login, String password, UserRole role) {
}
