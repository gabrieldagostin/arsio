package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.mapper.PaginationMapper;
import com.arsio.user.internal.infra.persistance.entity.FriendshipEntity;
import com.arsio.user.internal.infra.persistance.mapper.FriendshipEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataFriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaFriendshipRepositoryAdapter implements FriendshipRepository {

    private final SpringDataFriendshipRepository friendships;
    private final FriendshipEntityMapper mapper;
    private final PaginationMapper paginationMapper;

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
    public PageResult<Friendship> findAcceptedByUserId(UserId userId, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<FriendshipEntity> result = friendships.findAcceptedByUserId(
                userId.value(),
                FriendshipStatus.ACCEPTED,
                pageable
        );

        List<Friendship> friendshipList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                friendshipList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages());
    }

    @Override
    public PageResult<Friendship> findPendingReceivedByUserId(UserId userId, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<FriendshipEntity> result = friendships.findPendingReceivedByUserId(
                userId.value(),
                FriendshipStatus.PENDING,
                pageable
        );

        List<Friendship> friendshipList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                friendshipList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public PageResult<Friendship> findPendingSendByUserId(UserId userId, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<FriendshipEntity> result = friendships.findPendingSendByUserId(
                userId.value(),
                FriendshipStatus.PENDING,
                pageable
        );

        List<Friendship> friendshipList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                friendshipList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
