package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.service.AcceptFriendRequestService;
import com.arsio.user.internal.application.service.DeclineFriendRequestService;
import com.arsio.user.internal.application.service.GetMyFriendshipsService;
import com.arsio.user.internal.application.service.SendFriendRequestService;
import com.arsio.user.internal.infra.controller.dto.response.GetMyFriendshipsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendships")
public class FriendshipController {

    private final GetMyFriendshipsService getMyFriendshipsService;
    private final SendFriendRequestService sendFriendRequestService;
    private final AcceptFriendRequestService acceptFriendRequestService;
    private final DeclineFriendRequestService declineFriendRequestService;

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<List<GetMyFriendshipsResponse>> getMyFriendships() {

        List<GetMyFriendshipsResponse> responses = getMyFriendshipsService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/requests/{id}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable("id") UUID id) {

        sendFriendRequestService.execute(SecurityUtils.getCurrentUserId(), id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/requests/{friendshipId}/accept")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable("friendshipId") UUID friendshipId) {

        acceptFriendRequestService.execute(friendshipId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/requests/{friendshipId}/decline")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> declineFriendRequest(@PathVariable("friendshipId") UUID friendshipId) {

        declineFriendRequestService.execute(friendshipId);

        return ResponseEntity.noContent().build();
    }
}
