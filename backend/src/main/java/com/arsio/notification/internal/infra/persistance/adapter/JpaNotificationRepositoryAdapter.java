package com.arsio.notification.internal.infra.persistance.adapter;

import com.arsio.notification.internal.domain.model.Notification;
import com.arsio.notification.internal.domain.repository.NotificationRepository;
import com.arsio.notification.internal.infra.persistance.entity.NotificationEntity;
import com.arsio.notification.internal.infra.persistance.mapper.NotificationEntityMapper;
import com.arsio.notification.internal.infra.persistance.repository.SpringDataNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaNotificationRepositoryAdapter implements NotificationRepository {

    private final SpringDataNotificationRepository notifications;
    private final NotificationEntityMapper notificationMapper;

    @Override
    public void save(Notification notification) {
        NotificationEntity entity = notificationMapper.toEntity(notification);
        notifications.save(entity);
    }
}
