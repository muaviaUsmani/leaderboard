package com.muavia.leaderboard.services;

import com.google.gson.Gson;
import com.muavia.leaderboard.daos.ScoreDao;
import com.muavia.leaderboard.daos.UserDao;
import com.muavia.leaderboard.helpers.LeaderboardItem;
import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class UserService {
    private final UserDao userDao;
    private final ScoreDao scoreDao;

    @Autowired
    public UserService(@Qualifier("fakeUserDao") UserDao userDao,
                       @Qualifier("fakeScoreDao") ScoreDao scoreDao) {
        this.userDao = userDao;
        this.scoreDao = scoreDao;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public int store(User user) {
        return userDao.store(user);
    }

    public Optional<User> get(UUID id) {
        return userDao.get(id);
    }

    public int put(UUID id, User user) {
        return userDao.put(id, user);
    }

    public int delete(UUID id) {
        return userDao.delete(id);
    }

    public int addScore(Score score) {
        return scoreDao.store(score);
    }

    public List<LeaderboardItem> getLeaderboard(Integer n) {
        Gson gson = new Gson();

        List<Score> scores = scoreDao.getSortedN(n);
        List<LeaderboardItem> leaderboard = new ArrayList<>();
        for (int i=0; i<scores.size(); i++) {
            Integer playerScore = scores.get(i).getScore();
            Optional<User> user = get(scores.get(i).getUserId());
            System.out.println(new Gson().toJson(user));
            if (!user.isEmpty()) {
                LeaderboardItem entry = new LeaderboardItem(user.get().getName(), playerScore, user.get().getAvatar());
                leaderboard.add(entry);
            }
        }
        return leaderboard;
    }

    public int seedUsers() {
        List<User> users = userDao.seedUsers();
        Random rand = new Random();

        for (int i=0; i<users.size(); i++) {
            User user = users.get(i);
            Score score = new Score(UUID.randomUUID(), user.getId(), rand.nextInt(500));
            addScore(score);
        }
        return 1;
    }
}
