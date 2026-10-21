package com.arsio.review.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.review.internal.domain.model.Review;
import com.arsio.review.internal.domain.valueobject.Comment;
import com.arsio.review.internal.domain.valueobject.Rating;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.infra.persistence.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface ReviewEntityMapper {

    Review toDomain(ReviewEntity reviewEntity);

    @Mapping(target = "createdAt", ignore = true)
    ReviewEntity toEntity(Review review);

    default UUID reviewIdToUuid(ReviewId reviewId) {
        return reviewId.value();
    }

    default ReviewId uuidToReviewId(UUID value) {
        return new ReviewId(value);
    }

    default Integer ratingToInteger(Rating rating) {
        return rating.value();
    }

    default Rating integerToRating(Integer value) {
        return new Rating(value);
    }

    default String commentToString(Comment comment) {
        return comment.value();
    }

    default Comment stringToComment(String value) {
        return new Comment(value);
    }
}
