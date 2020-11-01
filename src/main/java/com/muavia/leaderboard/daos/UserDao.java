package com.muavia.leaderboard.daos;

import com.google.gson.Gson;
import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    int store(UUID id, User user);

    List<User> getAll();

    default int store(User user) {
        UUID id;
        if (user.getId() == null) {
            id = UUID.randomUUID();
        } else {
            id = user.getId();
        }
        System.out.println(id);
        return store(id, user);
    }

    Optional<User> get(UUID id);

    int put(UUID id, User user);

    int delete(UUID id);

    List<User> seedUsers();
}
