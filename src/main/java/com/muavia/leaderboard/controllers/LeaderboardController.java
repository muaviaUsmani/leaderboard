package com.muavia.leaderboard.controllers;

import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.User;
import com.muavia.leaderboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("leaderboard")
@RestController
public class LeaderboardController {
    private final UserService userService;

    @Autowired
    public LeaderboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAll(@RequestParam Optional<Integer> n, Model model) {

        model.addAttribute("players", userService.getLeaderboard(n.orElse(0)));
        return "index";
    }
}
