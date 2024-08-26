package com.demo.rockpaperscissors.controllers;

import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.services.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public GameResult createGame() {
        return gameService.createGame();
    }

    @GetMapping("/{option}")
    public GameResult getWinner(@PathVariable Option option) {
        Option computerOption = gameService.generateRandomOption();

        return gameService.playRound(option, computerOption);
    }
}
