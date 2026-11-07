package com.arsio.chat.internal.infra.persistence.adapter;

import com.arsio.chat.internal.domain.repository.ChatRepository;
import com.arsio.chat.internal.infra.persistence.mapper.MessageEntityMapper;
import com.arsio.chat.internal.infra.persistence.repository.SpringDataChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaChatRepositoryAdapter implements ChatRepository {

    private final MessageEntityMapper mapper;
    private final SpringDataChatRepository chats;
}
