CREATE TABLE review_votes (

    id UUID NOT NULL,

    review_id UUID NOT NULL ,

    user_id UUID NOT NULL,

    vote_type VARCHAR(30) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_comment_votes
        PRIMARY KEY (id),

    CONSTRAINT ck_comment_votes
        CHECK (
            vote_type IN (
                       'LIKE',
                       'DISLIKE'
                )
            ),

    CONSTRAINT fk_review_votes_review_id_reviews
        FOREIGN KEY (review_id)
            REFERENCES reviews(id)
);