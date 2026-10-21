package com.arsio.review.internal.infra.persistence.repository;

import com.arsio.review.internal.infra.persistence.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataReviewRepository extends JpaRepository<ReviewEntity, UUID> {
}
