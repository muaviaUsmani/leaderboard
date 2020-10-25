package com.muavia.leaderboard.daos;

import com.muavia.leaderboard.models.Score;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScoreDao {
    int store(UUID id, Score score);

    List<Score> getAll();

    List<Score> getSortedN(Integer n);

    default int store(Score score) {
        UUID id = UUID.randomUUID();
        return store(id, score);
    }

    Optional<Score> get(UUID id);

    int put(UUID id, Score score);

    int delete(UUID id);
}
