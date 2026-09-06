package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.service.*;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.infra.controller.dto.response.GetMyFriendshipsResponse;
import com.arsio.user.internal.infra.controller.mapper.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friendships")
public class FriendshipController {

    private final PaginationMapper paginationMapper;
    private final GetMyFriendshipsService getMyFriendshipsService;
    private final GetMyFriendshipsRequestsService getMyFriendshipsRequestsService;
    private final GetMyFriendshipsSendsService getMyFriendshipsSendsService;
    private final SendFriendRequestService sendFriendRequestService;
    private final AcceptFriendRequestService acceptFriendRequestService;
    private final DeclineFriendRequestService declineFriendRequestService;

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<GetMyFriendshipsResponse>> getMyFriendships(Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetMyFriendshipsResponse> responses =
                getMyFriendshipsService.execute(SecurityUtils.getCurrentUserId(), pagination);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/requests/received")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<GetMyFriendshipsResponse>> getMyFriendshipsRequests(Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetMyFriendshipsResponse> responses =
                getMyFriendshipsRequestsService.execute(SecurityUtils.getCurrentUserId(), pagination);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/requests/send")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<GetMyFriendshipsResponse>> getMyFriendshipsSends(Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetMyFriendshipsResponse> responses =
                getMyFriendshipsSendsService.execute(SecurityUtils.getCurrentUserId(), pagination);

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
