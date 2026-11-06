package com.arsio.review.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.review.internal.application.command.CreateReviewCommand;
import com.arsio.review.internal.application.usecase.CreateReviewUseCase;
import com.arsio.review.internal.application.usecase.GetReviewUseCase;
import com.arsio.review.internal.application.usecase.ListReviewsUseCase;
import com.arsio.review.internal.infra.controller.dto.request.CreateReviewRequest;
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
}
