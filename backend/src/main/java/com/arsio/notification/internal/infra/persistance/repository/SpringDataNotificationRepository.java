package com.arsio.notification.internal.infra.persistance.repository;

import com.arsio.notification.internal.infra.persistance.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataNotificationRepository extends JpaRepository<NotificationEntity, UUID> {
}
