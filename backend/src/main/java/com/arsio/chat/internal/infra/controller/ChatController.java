package com.arsio.chat.internal.infra.controller;

import com.arsio.chat.internal.infra.controller.mapper.ChatControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatController {

    private final ChatControllerMapper mapper;
}
