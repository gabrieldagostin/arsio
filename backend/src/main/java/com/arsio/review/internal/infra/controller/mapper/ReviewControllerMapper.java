package com.arsio.review.internal.infra.controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.review.internal.application.command.CreateReviewCommand;
import com.arsio.review.internal.domain.valueobject.Comment;
import com.arsio.review.internal.domain.valueobject.Rating;
import com.arsio.review.internal.infra.controller.dto.request.CreateReviewRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface ReviewControllerMapper {

    CreateReviewCommand toCreateReviewCommand(UUID userId, UUID gameId, CreateReviewRequest request);

    default Rating integerToRating(Integer value) {
        return new Rating(value);
    }

    default Comment stringToComment(String value) {
        return new Comment(value);
    }
}
