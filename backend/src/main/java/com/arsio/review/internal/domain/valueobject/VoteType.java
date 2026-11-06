package com.arsio.review.internal.domain.valueobject;

import com.arsio.review.internal.domain.exception.InvalidVoteTypeException;

public enum VoteType {

    LIKE(0, "Like"),
    DISLIKE(1, "Dislike");

    private final Integer cod;
    private final String description;

    VoteType(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public VoteType toEnum(Integer cod) {
        if (cod == null) return null;
        for (VoteType voteType : VoteType.values()) {
            if (cod.equals(voteType.cod)) return voteType;
        }
        throw new InvalidVoteTypeException();
    }
}
