package com.muavia.leaderboard.controllers;

import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;
import com.muavia.leaderboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("players")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void store(@Valid @NotNull @RequestBody User user) {
        userService.store(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path="{id}")
    public User get(@PathVariable("id") UUID id) {
        return userService.get(id).orElse(null);
    }

    @PutMapping(path="{id}")
    public void put(@PathVariable("id") UUID id,  @Valid @NotNull @RequestBody User user) {
        userService.put(id, user);
    }

    @DeleteMapping(path="{id}")
    public void delete(@PathVariable("id") UUID id) {
        userService.delete(id);
    }

    @PostMapping(path="{id}/score-add")
    public void storeScore(@RequestBody Score score) {
        userService.addScore(score);
    }
}
