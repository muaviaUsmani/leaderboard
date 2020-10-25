package com.muavia.leaderboard.controllers;

import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.models.Score;
import com.muavia.leaderboard.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("scores")
@RestController
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public void store(@Valid @NotNull @RequestBody Score score) {
        scoreService.store(score);
    }

    @GetMapping
    public List<Score> getAll() {
        return scoreService.getAll();
    }

    @GetMapping(path="{id}")
    public Score get(@PathVariable("id") UUID id) {
        return scoreService.get(id).orElse(null);
    }

    @PutMapping(path="{id}")
    public void put(@PathVariable("id") UUID id,  @Valid @NotNull @RequestBody Score score) {
        scoreService.put(id, score);
    }

    @DeleteMapping(path="{id}")
    public void delete(@PathVariable("id") UUID id) {
        scoreService.delete(id);
    }
}
