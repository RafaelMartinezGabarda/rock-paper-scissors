package com.demo.rockpaperscissors.business.model;

import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.model.enums.Result;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class GameResult {

    @Id
    @GeneratedValue
    private long Id;

    private Option playerOption;

    private int playerScore;

    private Option computerOption;

    private int computerScore;

    private Result result;

    public GameResult() {
        this.playerScore = 0;
        this.computerScore = 0;
    }
}
