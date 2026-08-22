package com.arsio.user.internal.infra.persistance.repository;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.infra.persistance.entity.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataFriendshipRepository extends JpaRepository<FriendshipEntity, UUID> {
}
