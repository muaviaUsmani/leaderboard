package com.muavia.leaderboard.services;

import com.muavia.leaderboard.daos.ScoreDao;
import com.muavia.leaderboard.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {
    private final ScoreDao scoreDao;

    @Autowired
    public ScoreService(@Qualifier("fakeScoreDao") ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public List<Score> getAll() {
        return scoreDao.getAll();
    }

    public int store(Score score) {
        return scoreDao.store(score);
    }

    public Optional<Score> get(UUID id) {
        return scoreDao.get(id);
    }

    public int put(UUID id, Score score) {
        return scoreDao.put(id, score);
    }

    public int delete(UUID id) {
        return scoreDao.delete(id);
    }
}
