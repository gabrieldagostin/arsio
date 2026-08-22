package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.repository.FriendRepository;
import com.arsio.user.internal.infra.persistance.repository.SpringDataFriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaFriendshipRepositoryAdapter implements FriendRepository {

    private final SpringDataFriendshipRepository friends;
    private final FriendshipEntityMapper mapper;

    @Override
    public void save(Friendship friendship) {
        friends.save(mapper)
    }
}
