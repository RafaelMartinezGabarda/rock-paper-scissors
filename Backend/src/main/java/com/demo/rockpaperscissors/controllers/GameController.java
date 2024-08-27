package com.demo.rockpaperscissors.controllers;

import com.demo.rockpaperscissors.business.exceptions.OptionNotFoundException;
import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.services.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<GameResult> createGame() {
        return ResponseEntity.ok(gameService.createGame());
    }

    @PutMapping("/play/{option}")
    public ResponseEntity<GameResult> playRound(@PathVariable String option) {
        if(Arrays.stream(Option.values()).noneMatch((o) -> o.name().equals(option))) {
            throw new OptionNotFoundException("Option not found");
        }

        Option computerOption = gameService.generateRandomOption();

        return ResponseEntity.ok(gameService.playRound(Option.valueOf(option), computerOption));
    }

    @GetMapping("/options")
    public ResponseEntity<Option[]> getOptions() {
        return ResponseEntity.ok(Option.values());
    }
}
