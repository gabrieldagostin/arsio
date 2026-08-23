package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.persistance.mapper.FriendshipEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataFriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaFriendshipRepositoryAdapter implements FriendshipRepository {

    private final SpringDataFriendshipRepository friendships;
    private final FriendshipEntityMapper mapper;

    @Override
    public void save(Friendship friendship) {
        friendships.save(mapper.toEntity(friendship));
    }

    @Override
    public Optional<Friendship> findById(FriendshipId friendshipId) {
        return friendships.findById(friendshipId.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Friendship> findAcceptedByUserId(UserId userId) {
        return friendships.findAcceptedByUserId(userId.value(), FriendshipStatus.ACCEPTED)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Friendship> findPendingReceivedByUserId(UserId userId) {
        return friendships.findPendingReceivedByUserId(userId.value(), FriendshipStatus.PENDING)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Friendship> findPendingSendByUserId(UserId userId) {
        return friendships.findPendingSendByUserId(userId.value(), FriendshipStatus.PENDING)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
