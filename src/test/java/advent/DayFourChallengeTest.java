package advent;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import advent.DayThreeChallenge.Element;

public class DayFourChallengeTest {

	DayFourChallenge challenge;
	
	List<String> testInput = Arrays.asList("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", 
											"Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
											"Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
											"Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
											"Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
											"Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
	@Before
	public void setUp() {
		challenge = new DayFourChallenge();
	}
	
	@Test
	public void testParsingCardNumber() {
		// GIVEN
		String example = "Card 123";
		
		// WHEN
		int result = challenge.lineToCardNumber(example);
		
		//THEN
		assertTrue(result == 123);
	}

	@Test
	public void testParsingNumbers() {
		// GIVEN
		
		String example = " 15 45 38 99 00";
		
		// WHEN
		List<Integer> result = challenge.lineToNumbers(example);
		
		//THEN
		assertTrue(result.contains(15));
		assertTrue(result.contains(15));
		assertTrue(result.contains(38));
		assertTrue(result.contains(99));
		assertTrue(result.contains(00));
	}
	
	
//	@Test
//	public void testExample() {
//		long result = challenge.solveChallenge(testInput);
//		Assert.assertTrue(result == 13);
//	}
	
		
}
