package com.arsio.notification.internal.infra.controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.notification.internal.application.command.SendEmailCommand;
import com.arsio.notification.internal.domain.valueobject.Message;
import com.arsio.notification.internal.domain.valueobject.Title;
import com.arsio.notification.internal.infra.controller.dto.request.SendEmailRequest;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface EmailControllerMapper {

    SendEmailCommand toSendEmailCommand(SendEmailRequest request);

    default Title stringToTitle(String string) {
        return new Title(string);
    }

    default Message  stringToMessage(String string) {
        return new Message(string);
    }
}
