package com.muavia.leaderboard.daos;

import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;
import com.muavia.leaderboard.services.ScoreService;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeUserDao")
public class FakeUserDataAccessService implements UserDao {
    private static List<User> DB = new ArrayList<>();

    @Override
    public List<User> seedUsers() {
        DB.add(new User(UUID.randomUUID(), "Alex Hernandez", "https://randomuser.me/api/portraits/men/7.jpg"));
        DB.add(new User(UUID.randomUUID(), "Mike Morris", "https://randomuser.me/api/portraits/men/21.jpg"));
        DB.add(new User(UUID.randomUUID(), "John Danaher", "https://randomuser.me/api/portraits/men/79.jpg"));
        DB.add(new User(UUID.randomUUID(), "Joe Rogan", "https://randomuser.me/api/portraits/men/94.jpg"));
        DB.add(new User(UUID.randomUUID(), "Justin Gaethje", "https://randomuser.me/api/portraits/men/62.jpg"));
        return DB;
    }

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
