package advent;

import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import advent.DayTwoChallenge.CubesGame;
import advent.DayTwoChallenge.CubesSet;

public class DayTwoChallengeTest {

	
	DayTwoChallenge challenge;
	
	List<String> testInput = Arrays.asList("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
			"Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
			"Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
			"Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
			"Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
	
	@Before
	public void setUp() {
		challenge = new DayTwoChallenge();
	}
	
	@Test
	public void testExample() {
		long result = challenge.solveChallenge(testInput, "12 red, 13 green, 14 blue");
		System.out.print(result);
		Assert.assertTrue(result == 8);
	}	
	
	@Test
	public void testExampleSecondPart() {
		long result = challenge.solveChallengeSecondPart(testInput);
		System.out.print(result);
		Assert.assertTrue(result == 2286);
	}
		
	@Test
	public void testFindingValidLines() {
		CubesGame game1 = challenge.createCubesGame("Game 1", Arrays.asList("2 red, 1 green, 1 blue", "2 green, 1 blue"));
		CubesGame game2 = challenge.createCubesGame("Game 2", Arrays.asList("2 red, 5 green, 1 blue", "2 green, 2 blue"));
		CubesGame game3 = challenge.createCubesGame("Game 3", Collections.emptyList());
	
		List<CubesGame> validGames = new ArrayList<>();
		validGames.add(game1);
		
		List<CubesGame> invalidGames = new ArrayList<>();
		invalidGames.add(game2);
		invalidGames.add(game3);
		
		List<CubesGame> testInput = new ArrayList<>();
		testInput.addAll(validGames);
		testInput.addAll(invalidGames);
		
		CubesSet referenceBag = challenge.createCubesSet("2 red, 3 green, 2 blue");
		
		List<CubesGame> result = challenge.validateCubesGames(testInput, referenceBag);
		Assert.assertTrue(result.size() == 1);
		CubesGame cubesGame = result.get(0);
		Assert.assertTrue(cubesGame.getId() == 1);
	}	
	
	@Test
	public void testGetMinimalCubes() {
		CubesGame game1 = challenge.createCubesGame("Game 1", Arrays.asList("5 red, 1 green, 1 blue", "2 green, 1 blue", "3 red, 1 green, 6 blue"));
		List<CubesGame> testInput = Arrays.asList(game1);
		List<CubesSet> cubesSets = challenge.getMinimalCubesSets(testInput);
		CubesSet result = cubesSets.get(0);
		Assert.assertTrue(result.getRedCubes() == 5);
		Assert.assertTrue(result.getGreenCubes() == 2);
		Assert.assertTrue(result.getBlueCubes() == 6);
	}	

	@Test
	public void testSumValidLines() {
		List<CubesGame> testInput = Arrays.asList(challenge.createCubesGame(1), challenge.createCubesGame(15), challenge.createCubesGame(29));
		long result = challenge.sumValidLines(testInput);
		Assert.assertTrue(result == 45);
	}	
	
	@Test
	public void sumPowerOfCubesSets() {
		List<CubesSet> testCubesSets = Arrays.asList(challenge.createCubesSet("3 green, 4 blue, 1 red"));
		long result = challenge.sumPowerOfCubesSets(testCubesSets);
		Assert.assertTrue(result == 12);
	}	
	
	@Test
	public void testParsingGameFromString() {
		CubesGame result1 = challenge.parseStringToCubesGame("Game 1");
		CubesGame result2 = challenge.parseStringToCubesGame("Game 125");
		Assert.assertTrue(result1.getId() == 1);
		Assert.assertTrue(result2.getId() == 125);
	}	
	
	@Test
	public void testCreatingCubesSetFromString() {
		CubesSet result = challenge.createCubesSet("3 green, 4 blue, 1 red");
		Assert.assertTrue(result.getRedCubes() == 1);
		Assert.assertTrue(result.getGreenCubes() == 3);
		Assert.assertTrue(result.getBlueCubes() == 4);
	}
	
	@Test
	public void testParsingStrinsToCubesSets() {
		List<CubesSet> resultList = challenge.parseStringsToCubeSets(Arrays.asList("3 green, 4 blue, 1 red", "8 green", "1 red, 5 blue, 21 green"));
		Assert.assertTrue(resultList.get(0).getRedCubes() == 1);
		Assert.assertTrue(resultList.get(1).getGreenCubes() == 8);
		Assert.assertTrue(resultList.get(2).getBlueCubes() == 5);
	}		
}
