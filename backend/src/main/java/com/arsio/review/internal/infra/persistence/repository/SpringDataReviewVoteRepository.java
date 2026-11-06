package com.arsio.review.internal.infra.persistence.repository;

import com.arsio.review.internal.infra.persistence.entity.ReviewVoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataReviewVoteRepository extends JpaRepository<ReviewVoteEntity, UUID> {
}