package com.arsio.review.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.review.internal.application.command.*;
import com.arsio.review.internal.application.usecase.*;
import com.arsio.review.internal.infra.controller.dto.request.CreateReviewRequest;
import com.arsio.review.internal.infra.controller.dto.request.UpdateReviewRequest;
import com.arsio.review.internal.infra.controller.dto.response.ReviewResponse;
import com.arsio.review.internal.infra.controller.mapper.ReviewControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewControllerMapper mapper;
    private final CreateReviewUseCase createReviewUseCase;
    private final ListReviewsUseCase listReviewsUseCase;
    private final GetReviewUseCase getReviewUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final LikeReviewUseCase likeReviewUseCase;
    private final DislikeReviewUseCase dislikeReviewUseCase;

    @PostMapping("/{gameId}/vote")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<ReviewResponse> createReview(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid CreateReviewRequest request) {

        CreateReviewCommand command = mapper.toCreateReviewCommand(gameId, SecurityUtils.getCurrentUserId(), request);

        ReviewResponse response = createReviewUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable(name = "gameId") UUID gameId) {

        List<ReviewResponse> responses = listReviewsUseCase.execute(gameId);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable(name = "reviewId") UUID reviewId) {

        ReviewResponse response = getReviewUseCase.execute(reviewId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable(name = "reviewId") UUID reviewId,
            @RequestBody @Valid UpdateReviewRequest request) {

        UpdateReviewCommand command = mapper.toUpdateReviewCommand(reviewId, request);

        ReviewResponse response = updateReviewUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> deleteReview(@PathVariable(name = "reviewId") UUID reviewId) {

        deleteReviewUseCase.execute(reviewId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{reviewId}/like")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> likeReview(@PathVariable(name = "reviewId") UUID reviewId) {

        LikeReviewCommand command = mapper.toLikeReviewCommand(reviewId, SecurityUtils.getCurrentUserId());

        likeReviewUseCase.execute(command);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{reviewId}/dislike")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> dislikeReview(@PathVariable(name = "reviewId") UUID reviewId) {

        DislikeReviewCommand command = mapper.toDislikeReviewCommand(reviewId, SecurityUtils.getCurrentUserId());

        dislikeReviewUseCase.execute(command);

        return ResponseEntity.ok().build();
    }
}
