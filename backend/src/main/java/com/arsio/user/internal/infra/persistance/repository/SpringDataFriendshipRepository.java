package com.arsio.user.internal.infra.persistance.repository;

import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.infra.persistance.entity.FriendshipEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataFriendshipRepository extends JpaRepository<FriendshipEntity, UUID> {

    @Query("""
        SELECT f
        FROM FriendshipEntity f
        WHERE f.status = :status
          AND (
              f.requesterId = :userId
              OR f.addresseeId = :userId
          )
        """)
    List<FriendshipEntity> findAcceptedByUserId(
            @Param("userId") UUID userId,
            @Param("status") FriendshipStatus status
    );
}
