package com.muavia.leaderboard.daos;

import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;
import com.muavia.leaderboard.services.ScoreService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeUserDao")
public class FakeUserDataAccessService implements UserDao {
    private static List<User> DB = new ArrayList<>();

    @Override
    public int store(UUID id, User user) {
        DB.add(new User(id, user.getName(), user.getAvatar()));
        return 1;
    }

    @Override
    public List<User> getAll() {
        return DB;
    }

    @Override
    public Optional<User> get(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int put(UUID id, User user) {
        return get(id)
                .map(u -> {
                    int existingUserIndex = DB.indexOf(u);
                    if (existingUserIndex >= 0) {
                        DB.set(existingUserIndex, new User(id, user.getName(), user.getAvatar()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int delete(UUID id) {
        Optional<User> user = get(id);
        if (user.isEmpty()) {
            return 0;
        }
        DB.remove(user.get());
        return 1;
    }
}
