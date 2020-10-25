package com.muavia.leaderboard.daos;

import com.muavia.leaderboard.helpers.ScoreComparator;
import com.muavia.leaderboard.models.Score;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeScoreDao")
public class FakeScoreDataAccessService implements ScoreDao {
    private static List<Score> DB = new ArrayList<>();

    @Override
    public int store(UUID id, Score score) {
        DB.add(new Score(id, score.getUserId(), score.getScore()));
        return 1;
    }

    @Override
    public List<Score> getAll() {
        return DB;
    }

    @Override
    public List<Score> getSortedN(Integer n) {
        Collections.sort(DB, new ScoreComparator());
        if (n  != 0)
            return DB.subList(0, n);
        return DB;
    }

    @Override
    public Optional<Score> get(UUID id) {
        return DB.stream()
                .filter(score -> score.getId().equals(id))
                .findFirst();
    }

    @Override
    public int put(UUID id, Score score) {
        return get(id)
                .map(s -> {
                    int existingScoreIndex = DB.indexOf(s);
                    if (existingScoreIndex >= 0) {
                        DB.set(existingScoreIndex, new Score(id, score.getUserId(), score.getScore()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int delete(UUID id) {
        Optional<Score> score = get(id);
        if (score.isEmpty()) {
            return 0;
        }
        DB.remove(score.get());
        return 1;
    }
}
