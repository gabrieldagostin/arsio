package com.arsio.notification.internal.domain.model;

import com.arsio.notification.internal.domain.valueobject.Message;
import com.arsio.notification.internal.domain.valueobject.NotificationId;
import com.arsio.notification.internal.domain.valueobject.Title;
import com.arsio.shared.valueobject.UserId;

public class Notification {

    private final NotificationId id;
    private final UserId userId;
    private Title title;
    private Message message;
    private boolean read;

    public Notification(NotificationId id, UserId userId, Title title, Message message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.read = read;
    }

    public NotificationId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public Title getTitle() {
        return title;
    }

    public Message getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }
}
