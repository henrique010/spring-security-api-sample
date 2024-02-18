package com.example.securityjwt.domain.email;

import java.util.UUID;

public record EmailDTO(
        UUID ownerRef,
        String emailFrom,
        String emailTo,
        String subject,
        String content) {
}
