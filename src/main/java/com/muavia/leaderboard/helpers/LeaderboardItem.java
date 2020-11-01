package com.muavia.leaderboard.helpers;

public class LeaderboardItem {
    String name;
    int score;
    String avatar;

    public LeaderboardItem(String name, int score, String avatar) {
        this.name = name;
        this.score = score;
        this.avatar = avatar;
    }

    public String getName() {
        return this.name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public int getScore() {
        return this.score;
    }
}
