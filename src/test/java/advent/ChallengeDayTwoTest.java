package advent;

import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import advent.ChallengeDayTwo.Game;
import advent.ChallengeDayTwo.Subset;

public class ChallengeDayTwoTest {

	
	ChallengeDayTwo challenge;
	
	@Before
	public void setUp() {
		challenge = new ChallengeDayTwo();
	}
	
	@Test
	public void testExample() {
		List<String> testInput = Arrays.asList("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
				"Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
				"Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
				"Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
				"Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
		long result = challenge.solveChallenge(testInput, "12 red, 13 green, 14 blue");
		System.out.print(result);
		Assert.assertTrue(result == 8);
	}	
	
	@Test
	public void testFindingValidLines() {
		Game game1 = challenge.createGame("Game 1", Arrays.asList("2 red, 1 green, 1 blue", "2 green, 1 blue"));
		Game game2 = challenge.createGame("Game 2", Arrays.asList("2 red, 5 green, 1 blue", "2 green, 2 blue"));
		Game game3 = challenge.createGame("Game 3", Collections.emptyList());
	
		List<Game> validGames = new ArrayList<>();
		validGames.add(game1);
		
		List<Game> invalidGames = new ArrayList<>();
		invalidGames.add(game2);
		invalidGames.add(game3);
		
		List<Game> testInput = new ArrayList<>();
		testInput.addAll(validGames);
		testInput.addAll(invalidGames);
		
		Subset referenceBag = challenge.createSubset("2 red, 3 green, 2 blue");
		
		List<Game> result = challenge.validateGames(testInput, referenceBag);
		Assert.assertTrue(result.size() == 1);
		Game gameR = result.get(0);
		Assert.assertTrue(gameR.getId() == 1);
	}	

	@Test
	public void testSumValidLines() {
		List<Game> testInput = Arrays.asList(challenge.createGame(1), challenge.createGame(15), challenge.createGame(29));
		long result = challenge.sumValidLines(testInput);
		Assert.assertTrue(result == 45);
	}	
	
	@Test
	public void testParsingGame() {
		Game result1 = challenge.parseGame("Game 1");
		Game result2 = challenge.parseGame("Game 125");
		Assert.assertTrue(result1.getId() == 1);
		Assert.assertTrue(result2.getId() == 125);
	}	
	
	@Test
	public void testCreatingSubset() {
		Subset result = challenge.createSubset("3 green, 4 blue, 1 red");
		Assert.assertTrue(result.getRed() == 1);
		Assert.assertTrue(result.getGreen() == 3);
		Assert.assertTrue(result.getBlue() == 4);
	}
	
	@Test
	public void testParsingSubsets() {
		List<Subset> resultList = challenge.parseSubsets(Arrays.asList("3 green, 4 blue, 1 red", "8 green", "1 red, 5 blue, 21 green"));
		Assert.assertTrue(resultList.get(0).getRed() == 1);
		Assert.assertTrue(resultList.get(1).getGreen() == 8);
		Assert.assertTrue(resultList.get(2).getBlue() == 5);
	}		
}
