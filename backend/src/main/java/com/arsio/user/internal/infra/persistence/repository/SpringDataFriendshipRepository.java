package com.arsio.user.internal.infra.persistence.repository;

import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.infra.persistence.entity.FriendshipEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
        ORDER BY f.createdAt ASC, f.id ASC
        """)
    Page<FriendshipEntity> findAcceptedByUserId(
            @Param("userId") UUID userId,
            @Param("status") FriendshipStatus status,
            Pageable pageable
    );

    @Query("""
        SELECT f
        FROM FriendshipEntity f
        WHERE f.status = :status
            AND (
                f.addresseeId = :userId
            )
        ORDER BY f.createdAt ASC, f.id ASC
        """)
    Page<FriendshipEntity> findPendingReceivedByUserId(
            @Param("userId") UUID userId,
            @Param("status") FriendshipStatus status,
            Pageable pageable
    );

    @Query("""
        SELECT f
        FROM FriendshipEntity f
        WHERE f.status = :status
            AND (
                f.requesterId = :userId
            )
        ORDER BY f.createdAt ASC, f.id ASC
        """)
    Page<FriendshipEntity> findPendingSendByUserId(
            @Param("userId") UUID userId,
            @Param("status") FriendshipStatus status,
            Pageable pageable
    );
}
