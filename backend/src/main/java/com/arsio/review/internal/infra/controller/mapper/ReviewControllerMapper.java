package com.arsio.review.internal.infra.controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.review.internal.application.command.CreateReviewCommand;
import com.arsio.review.internal.application.command.DislikeReviewCommand;
import com.arsio.review.internal.application.command.LikeReviewCommand;
import com.arsio.review.internal.application.command.UpdateReviewCommand;
import com.arsio.review.internal.domain.valueobject.Comment;
import com.arsio.review.internal.domain.valueobject.Rating;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.infra.controller.dto.request.CreateReviewRequest;
import com.arsio.review.internal.infra.controller.dto.request.UpdateReviewRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface ReviewControllerMapper {

    CreateReviewCommand toCreateReviewCommand(UUID userId, UUID gameId, CreateReviewRequest request);

    LikeReviewCommand toLikeReviewCommand(UUID reviewId, UUID userId);

    DislikeReviewCommand toDislikeReviewCommand(UUID reviewId, UUID userId);

    UpdateReviewCommand toUpdateReviewCommand(UUID reviewId, UpdateReviewRequest request);

    default ReviewId toReviewId(UUID value) {
        return new ReviewId(value);
    }

    default Rating integerToRating(Integer value) {
        return new Rating(value);
    }

    default Comment stringToComment(String value) {
        return new Comment(value);
    }
}
