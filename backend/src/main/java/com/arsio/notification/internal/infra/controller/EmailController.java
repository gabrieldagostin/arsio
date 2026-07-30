package com.arsio.notification.internal.infra.controller;

import com.arsio.config.security.SecurityConfigurations;
import com.arsio.notification.internal.application.dto.SendEmailCommand;
import com.arsio.notification.internal.application.service.EmailService;
import com.arsio.notification.internal.infra.controller.dto.SendEmailRequest;
import com.arsio.notification.internal.infra.controller.mapper.EmailControllerMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final EmailControllerMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid SendEmailRequest request) {

        SendEmailCommand command = mapper.toSendEmailCommand(request);

        emailService.sendEmail(command);

        return ResponseEntity.noContent().build();
    }
}
