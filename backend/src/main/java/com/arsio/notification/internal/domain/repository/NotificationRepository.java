package com.arsio.notification.internal.domain.repository;

import com.arsio.notification.internal.domain.model.Notification;

public interface NotificationRepository {

    void save(Notification notification);
}
