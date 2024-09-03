package com.demo.rockpaperscissors.business.services.game;

import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;

public interface IGameService {
    GameResult createGame();
    Option generateRandomOption();
    GameResult playRound(long id, Option playerOption, Option computerOption);
}
