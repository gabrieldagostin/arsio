package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.service.AcceptFriendRequestService;
import com.arsio.user.internal.application.service.DeclineFriendRequestService;
import com.arsio.user.internal.application.service.SendFriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendships")
public class FriendshipController {

    private final SendFriendRequestService sendFriendRequestService;
    private final AcceptFriendRequestService acceptFriendRequestService;
    private final DeclineFriendRequestService declineFriendRequestService;

    @PostMapping("/friendships/requests/{id}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable("id") UUID id) {

        sendFriendRequestService.execute(SecurityUtils.getCurrentUserId(), id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/friendships/requests/{friendshipId}/accept")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable("friendshipId") UUID friendshipId) {

        acceptFriendRequestService.execute(friendshipId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/friendships/requests/{friendshipId}/decline")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> declineFriendRequest(@PathVariable("friendshipId") UUID friendshipId) {

        declineFriendRequestService.execute(friendshipId);

        return ResponseEntity.noContent().build();
    }
}
