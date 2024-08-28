package com.demo.rockpaperscissors;

import com.demo.rockpaperscissors.business.exceptions.GameNotFoundException;
import com.demo.rockpaperscissors.business.model.GameResult;
import com.demo.rockpaperscissors.business.model.enums.Option;
import com.demo.rockpaperscissors.business.model.enums.Result;
import com.demo.rockpaperscissors.business.services.game.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RockpaperscissorsApplicationTests {
	private GameService gameService;

	@BeforeEach
	void setUp() {
		gameService = new GameService();
	}

	@Test
	void testCreateGame() {
		GameResult gameResult = gameService.createGame();

		assertNotNull(gameResult, "GameResult should not be null");
		assertEquals(0, gameResult.getPlayerScore(), "Initial player score should be 0");
		assertEquals(0, gameResult.getComputerScore(), "Initial computer score should be 0");
	}

	@Test
	void testGenerateRandomOption() {
		Option randomOption = gameService.generateRandomOption();

		assertNotNull(randomOption, "Random option should not be null");
		assertTrue(EnumSet.allOf(Option.class).contains(randomOption), "Random option should be a valid enum constant");
	}

	@Test
	void testPlayRoundWithoutGameThrowsException() {
		Option playerOption = Option.ROCK;
		Option computerOption = Option.PAPER;

		assertThrows(GameNotFoundException.class, () -> gameService.playRound(playerOption, computerOption),
				"Playing a round without a game should throw GameNotFoundException");
	}

	@Test
	void testPlayRoundWithGameAndPlayerWins() {
		gameService.createGame();
		Option playerOption = Option.ROCK;
		Option computerOption = Option.SCISSORS;

		GameResult result = gameService.playRound(playerOption, computerOption);

		assertEquals(Result.WIN, result.getResult(), "Result should be WIN for player");
		assertEquals(1, result.getPlayerScore(), "Player score should be incremented by 1");
		assertEquals(0, result.getComputerScore(), "Computer score should remain 0");
	}

	@Test
	void testPlayRoundWithGameAndComputerWins() {
		gameService.createGame();
		Option playerOption = Option.ROCK;
		Option computerOption = Option.PAPER;

		GameResult result = gameService.playRound(playerOption, computerOption);

		assertEquals(Result.LOSE, result.getResult(), "Result should be LOSE for player");
		assertEquals(0, result.getPlayerScore(), "Player score should remain 0");
		assertEquals(1, result.getComputerScore(), "Computer score should be incremented by 1");
	}

	@Test
	void testPlayRoundWithGameAndDraw() {
		gameService.createGame();
		Option playerOption = Option.ROCK;
		Option computerOption = Option.ROCK;

		GameResult result = gameService.playRound(playerOption, computerOption);

		assertEquals(Result.DRAW, result.getResult(), "Result should be DRAW");
		assertEquals(1, result.getPlayerScore(), "Player score should be incremented by 1");
		assertEquals(1, result.getComputerScore(), "Computer score should be incremented by 1");
	}

	@Test
	void testRockBeatsScissors() {
		assertTrue(Option.ROCK.beats(Option.SCISSORS));
	}

	@Test
	void testScissorsBeatsPaper() {
		assertTrue(Option.SCISSORS.beats(Option.PAPER));
	}

	@Test
	void testPaperBeatsRock() {
		assertTrue(Option.PAPER.beats(Option.ROCK));
	}
}
