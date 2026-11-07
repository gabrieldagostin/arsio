package com.arsio.chat.internal.infra.persistence.mapper;

import com.arsio.chat.internal.domain.model.Message;
import com.arsio.chat.internal.domain.valueobject.Content;
import com.arsio.chat.internal.domain.valueobject.MessageId;
import com.arsio.chat.internal.infra.persistence.entity.MessageEntity;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface MessageEntityMapper {

    Message toDomain(MessageEntity messageEntity);

    @Mapping(target = "createdAt", ignore = true)
    MessageEntity toEntity(Message message);

    default UUID messageIdToUuid(MessageId messageId) {
        return messageId.value();
    }

    default MessageId uuidToMessageId(UUID value) {
        return new MessageId(value);
    }

    default String contentToString(Content content) {
        return content.value();
    }

    default Content stringToContent(String value) {
        return new Content(value);
    }
}
