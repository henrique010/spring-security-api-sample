package com.example.securityjwt.service;

import com.example.securityjwt.domain.email.EmailDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.mail.from}")
    private String emailFrom;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String message, String emailTo, String subject) {
        EmailDTO data = new EmailDTO(
                UUID.randomUUID(),
                emailFrom,
                emailTo,
                subject,
                message
        );
        this.amqpTemplate.convertAndSend(this.queue, data);
    }
}
