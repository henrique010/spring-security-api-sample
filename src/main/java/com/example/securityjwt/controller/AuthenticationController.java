package com.example.securityjwt.controller;

import com.example.securityjwt.domain.user.AuthenticationDTO;
import com.example.securityjwt.domain.user.LoginResponseDTO;
import com.example.securityjwt.domain.user.RegisterUserDTO;
import com.example.securityjwt.domain.user.User;
import com.example.securityjwt.service.EmailService;
import com.example.securityjwt.service.TokenService;
import com.example.securityjwt.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private TokenService tokenService;
    private EmailService emailService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        String token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterUserDTO data) {
        this.userService.register(data);
        this.emailService.send(
                "Cadastro na plataforma realizado com sucesso",
                data.login(),
                "Bem vindo a plataforma"
        );
        return ResponseEntity.ok().build();
    }
}
