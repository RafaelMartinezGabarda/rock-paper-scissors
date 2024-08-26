package com.demo.rockpaperscissors.business.services.game;

import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.model.enums.Result;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService implements IGameService {
    private GameResult gameResult;

    @Override
    public GameResult createGame() {
        gameResult = new GameResult();

        return gameResult;
    }

    @Override
    public Option generateRandomOption() {
        return Option.values()[new Random().nextInt(Option.values().length)];
    }

    @Override
    public GameResult playRound(Option playerOption, Option computerOption) {
        gameResult.setPlayerOption(playerOption);
        gameResult.setComputerOption(computerOption);

        Result roundResult = this.evaluateResult();

        gameResult.setResult(roundResult);
        this.setScore(roundResult);

        return gameResult;
    }

    private Result evaluateResult(){
        if(gameResult.getPlayerOption().equals(gameResult.getComputerOption())){
            return Result.DRAW;
        }

        return gameResult.getPlayerOption().beats(gameResult.getComputerOption()) ? Result.WIN : Result.LOSE;
    }

    private void setScore(Result result) {
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
