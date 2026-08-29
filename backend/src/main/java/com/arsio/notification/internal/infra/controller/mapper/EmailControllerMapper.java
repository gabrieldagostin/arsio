package com.arsio.notification.internal.infra.controller.mapper;

import com.arsio.notification.internal.application.command.SendEmailCommand;
import com.arsio.notification.internal.infra.controller.dto.request.SendEmailRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface EmailControllerMapper {

    SendEmailCommand toSendEmailCommand(SendEmailRequest request);
}
