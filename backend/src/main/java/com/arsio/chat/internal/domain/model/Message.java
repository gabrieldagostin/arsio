package com.arsio.chat.internal.domain.model;

import com.arsio.chat.internal.domain.valueobject.Content;
import com.arsio.chat.internal.domain.valueobject.MessageId;

public class Message {

    private final MessageId id;
    private final MessageId senderId;
    private final MessageId receiverId;
    private Content content;

    public Message(MessageId id, MessageId senderId, MessageId receiverId, Content content) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public MessageId getId() {
        return id;
    }

    public MessageId getSenderId() {
        return senderId;
    }

    public MessageId getReceiverId() {
        return receiverId;
    }

    public Content getContent() {
        return content;
    }
}
