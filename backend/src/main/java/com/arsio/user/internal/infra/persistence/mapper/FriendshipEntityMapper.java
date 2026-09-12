package com.arsio.user.internal.infra.persistence.mapper;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.persistence.entity.FriendshipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface FriendshipEntityMapper {

    Friendship toDomain(FriendshipEntity friendshipEntity);

    @Mapping(target = "createdAt", ignore = true)
    FriendshipEntity toEntity(Friendship friendship);

    default UUID friendshipIdToUuid(FriendshipId friendshipId) {
        return friendshipId.value();
    }

    default FriendshipId uuidToFriendshipId(UUID value) {
        return new FriendshipId(value);
    }

    default UUID userIdToUuid(UserId userId) {
        return userId.value();
    }

    default UserId uuidToUserId(UUID value) {
        return value != null ? new UserId(value) : null;
    }
}
