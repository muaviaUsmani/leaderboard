package com.muavia.leaderboard.controllers;

import com.google.gson.Gson;
import com.muavia.leaderboard.helpers.LeaderboardItem;
import com.muavia.leaderboard.helpers.PlayerData;
import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;
import com.muavia.leaderboard.services.ScoreService;
import com.muavia.leaderboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("leaderboard")
@Controller
public class LeaderboardController {
    private final UserService userService;
    private final ScoreService scoreService;

    @Autowired
    public LeaderboardController(UserService userService, ScoreService scoreService) {
        this.userService = userService;
        this.scoreService = scoreService;
    }

    @GetMapping(path="seed")
    public String seed() {
        userService.seedUsers();
        return "redirect:/leaderboard";
    }

    @GetMapping
    public String getAll(@RequestParam Optional<Integer> n, Model model) {
        List<LeaderboardItem> players = userService.getLeaderboard(n.orElse(0));
        model.addAttribute("players", players);
        return "classpath:templates/index";
    }

    @ModelAttribute
    public void playerData(Model model) {
        model.addAttribute("playerData", new PlayerData());
    }

    @PostMapping
    public String store(@ModelAttribute PlayerData playerData, Model model) {
        model.addAttribute("playerData", playerData);
        UUID userUuid = UUID.randomUUID();
        User user = new User(userUuid, playerData.getName(), playerData.getAvatar());
        System.out.println(new Gson().toJson(user));
        userService.store(user);
        userService.addScore(new Score(UUID.randomUUID(), userUuid, playerData.getScore()));

        return "redirect:/leaderboard";
    }
}
