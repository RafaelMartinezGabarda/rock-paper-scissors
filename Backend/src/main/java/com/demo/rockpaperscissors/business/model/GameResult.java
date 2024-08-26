package com.demo.rockpaperscissors.business.model;

import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.model.enums.Result;

public class GameResult {
    private Option playerOption;

    private int playerScore;

    private Option computerOption;

    private int computerScore;

    private Result result;

    public Option getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(Option playerOption) {
        this.playerOption = playerOption;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public Option getComputerOption() {
        return computerOption;
    }

    public void setComputerOption(Option computerOption) {
        this.computerOption = computerOption;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
