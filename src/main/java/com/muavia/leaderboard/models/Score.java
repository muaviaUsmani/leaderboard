package com.muavia.leaderboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Score {
    private final UUID id;
    private final UUID userId;
    private final int score;

    public Score(@JsonProperty("id") UUID id,
                 @JsonProperty("userId") UUID userId,
                 @JsonProperty("score") int score) {
        this.id = id;
        this.userId = userId;
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }
}
