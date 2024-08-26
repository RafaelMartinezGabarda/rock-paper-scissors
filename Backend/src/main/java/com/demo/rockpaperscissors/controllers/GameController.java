package com.demo.rockpaperscissors.controllers;

import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.services.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public GameResult createGame() {
        return gameService.createGame();
    }

    @PutMapping("/play/{option}")
    public GameResult getWinner(@PathVariable Option option) {
        Option computerOption = gameService.generateRandomOption();

        return gameService.playRound(option, computerOption);
    }

    @GetMapping("/options")
    public Option[] getOptions() {
        return Option.values();
    }
}
