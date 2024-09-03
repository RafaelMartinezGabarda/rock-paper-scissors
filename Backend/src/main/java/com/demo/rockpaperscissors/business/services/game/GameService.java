package com.demo.rockpaperscissors.business.services.game;

import com.demo.rockpaperscissors.business.exceptions.GameNotFoundException;
import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.model.enums.Result;
import com.demo.rockpaperscissors.business.repositories.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService implements IGameService {
    private final GameResultRepository gameRepository;

    @Autowired
    public GameService(GameResultRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameResult createGame() {
        GameResult gameResult = new GameResult();

        return gameRepository.save(gameResult);
    }

    @Override
    public Option generateRandomOption() {
        return Option.values()[new Random().nextInt(Option.values().length)];
    }

    @Override
    public GameResult playRound(long id, Option playerOption, Option computerOption) {
        GameResult gameResult = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("No game found"));

        gameResult.setPlayerOption(playerOption);
        gameResult.setComputerOption(computerOption);

        Result roundResult = this.evaluateResult(gameResult);

        gameResult.setResult(roundResult);
        this.setScore(gameResult, roundResult);

        return gameRepository.save(gameResult);
    }

    private Result evaluateResult(GameResult gameResult){
        if(gameResult.getPlayerOption().equals(gameResult.getComputerOption())){
            return Result.DRAW;
        }

        return gameResult.getPlayerOption().beats(gameResult.getComputerOption()) ? Result.WIN : Result.LOSE;
    }

    private void setScore(GameResult gameResult, Result result) {
        if(result.equals(Result.DRAW)){
            gameResult.setPlayerScore(gameResult.getPlayerScore() + 1);
            gameResult.setComputerScore(gameResult.getComputerScore() + 1);
        } else if (result.equals(Result.WIN)) {
            gameResult.setPlayerScore(gameResult.getPlayerScore() + 1);
        } else if (result.equals(Result.LOSE)) {
            gameResult.setComputerScore(gameResult.getComputerScore() + 1);
        }
    }
}
