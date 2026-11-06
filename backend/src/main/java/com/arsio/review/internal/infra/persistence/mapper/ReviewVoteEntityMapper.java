package com.arsio.review.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.review.internal.domain.model.ReviewVote;
import com.arsio.review.internal.domain.valueobject.ReviewId;
import com.arsio.review.internal.domain.valueobject.VoteType;
import com.arsio.review.internal.infra.persistence.entity.ReviewVoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface ReviewVoteEntityMapper {

    ReviewVote toDomain(ReviewVoteEntity reviewVoteEntity);

    @Mapping(target = "createdAt", ignore = true)
    ReviewVoteEntity toEntity(ReviewVote reviewVote);

    default UUID reviewVoteIdToUuid(ReviewId reviewId) {
        return reviewId.value();
    }

    default ReviewId uuidToReviewId(UUID value) {
        return new ReviewId(value);
    }

    default String voteTypeToString(VoteType voteType) {
        return voteType.name();
    }

    default VoteType stringToVoteType(String value) {
        return VoteType.valueOf(value);
    }
}
