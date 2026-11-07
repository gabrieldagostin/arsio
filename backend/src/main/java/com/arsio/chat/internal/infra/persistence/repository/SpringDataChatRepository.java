package com.arsio.chat.internal.infra.persistence.repository;

import com.arsio.chat.internal.infra.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataChatRepository extends JpaRepository<MessageEntity, UUID> {
}
