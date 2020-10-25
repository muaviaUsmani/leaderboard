package com.muavia.leaderboard.daos;

import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    int store(UUID id, User user);

    List<User> getAll();

    default int store(User user) {
        UUID id = UUID.randomUUID();
        return store(id, user);
    }

    Optional<User> get(UUID id);

    int put(UUID id, User user);

    int delete(UUID id);
}
