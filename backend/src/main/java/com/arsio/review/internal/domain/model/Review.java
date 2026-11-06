package com.arsio.review.internal.domain.model;

import com.arsio.review.internal.domain.valueobject.Comment;
import com.arsio.review.internal.domain.valueobject.Rating;
import com.arsio.review.internal.domain.valueobject.ReviewId;

import java.util.UUID;

public class Review {

    private final ReviewId id;
    private final UUID userId;
    private final UUID gameId;
    private Rating rating;
    private Comment comment;
    private boolean active;

    public Review(ReviewId id, UUID userId, UUID gameId, Rating rating, Comment comment, boolean active) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.rating = rating;
        this.comment = comment;
        this.active = active;
    }

    public static Review create(UUID userId, UUID gameId, Rating rating, Comment comment) {
        return new Review(
                ReviewId.generate(),
                userId,
                gameId,
                rating,
                comment,
                true
        );
    }

    public ReviewId getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public Rating getRating() {
        return rating;
    }

    public Comment getComment() {
        return comment;
    }
}
