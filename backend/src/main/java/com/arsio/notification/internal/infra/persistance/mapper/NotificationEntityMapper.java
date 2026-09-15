package com.arsio.notification.internal.infra.persistance.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.notification.internal.domain.model.Notification;
import com.arsio.notification.internal.domain.valueobject.Message;
import com.arsio.notification.internal.domain.valueobject.NotificationId;
import com.arsio.notification.internal.domain.valueobject.Title;
import com.arsio.notification.internal.infra.persistance.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config =  CentralMapperConfig.class)
public interface NotificationEntityMapper {

    Notification toDomain(NotificationEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    NotificationEntity toEntity(Notification domain);

    default UUID notificationIdToUuid(NotificationId notificationId) {
        return notificationId.value();
    }

    default NotificationId uuidToNotificationId(UUID value) {
        return new NotificationId(value);
    }

    default String titleToString(Title title) {
        return title.value();
    }

    default Title stringToTitle(String value) {
        return new Title(value);
    }

    default String messageToString(Message message) {
        return message.value();
    }

    default Message stringToMessage(String value) {
        return new Message(value);
    }
}
