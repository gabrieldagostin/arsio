package com.arsio.user.internal.domain.repository;

import com.arsio.user.internal.domain.model.Friendship;

public interface FriendRepository {

    void save(Friendship friendship);
}
