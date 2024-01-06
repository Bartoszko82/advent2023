package advent;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DayThreeChallengeTest {

	DayThreeChallenge challenge;
	
	List<String> testInput = Arrays.asList("467..114..","...*......","..35..633.","......#...","617*......",
			".....+.58.","..592.....","......755.","...$.*....",".664.598..");

	
	@Before
	public void setUp() {
		challenge = new DayThreeChallenge();
	}
	
	@Test
	public void testExample() {
		long result = challenge.solveChallenge(testInput);
		System.out.print(result);
		Assert.assertTrue(result == 4361);
	}	

	
	
	
}
