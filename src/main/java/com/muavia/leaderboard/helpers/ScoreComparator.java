package com.muavia.leaderboard.helpers;

import com.muavia.leaderboard.models.Score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    public int compare(Score s1, Score s2) {
        if (s1.getScore() < s2.getScore()) {
            return 1;
        }
        return -1;
    }
}
