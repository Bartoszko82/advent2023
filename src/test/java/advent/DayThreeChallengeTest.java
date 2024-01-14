package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import advent.DayThreeChallenge.Element;

public class DayThreeChallengeTest {

	DayThreeChallenge challenge;
	
	List<String> testInput = Arrays.asList("467..114..","...*......","..35..633.","......#...","617*......",
			".....+.58.","..592.....","......755.","...$.*....",".664.598..");

	
	@Before
	public void setUp() {
		challenge = new DayThreeChallenge();
	}
	
//	@Test
//	public void testExample() {
//		long result = challenge.solveChallenge(testInput);
//		Assert.assertTrue(result == 4361);
//	}	

	@Test
	public void testParsingLines() {
		Map<Integer, List<Element>> parts = challenge.parseElements(Arrays.asList("..456....123....", ".12....158"));
		Map<Integer, List<Element>> symbols = challenge.parseElements(Arrays.asList(".&....#....@"));
		Assert.assertTrue(parts.size() == 2);
		Assert.assertTrue(parts.get(0).size() == 2 && parts.get(1).size() == 2 );
		Assert.assertTrue(allEngineParts(parts.get(0)) && allEngineParts(parts.get(1)));		
		Assert.assertTrue(getValuesAsStrings(parts.get(0)).equals(Arrays.asList("456","123")));
		Assert.assertTrue(getValuesAsStrings(parts.get(1)).equals(Arrays.asList("12","158")));
		Assert.assertTrue(symbols.get(0).size() == 3);
		Assert.assertTrue(getValuesAsStrings(symbols.get(0)).equals(Arrays.asList("&","#","@")));
	}
	
	private List<String> getValuesAsStrings (List<Element> elements) {
		return elements.stream().map(e -> e.getElement()).collect(Collectors.toList());
	}
 	
	private boolean allEngineParts(List<Element> elements) {
		return elements.stream().allMatch(e -> e.isEnginePart);
	}
	
	@Test
	public void testSumEngineParts() {
		Element firstElement = challenge.createElement("456", false, 1, 4);
		Element secondElement = challenge.createElement("1528", false, 1,54);
		List<Element> engineParts = new ArrayList<>();
		engineParts.add(firstElement);
		engineParts.add(secondElement);
		long result = challenge.sumEngineParts(engineParts);
		Assert.assertTrue(result == 1984);
	}
	
	
	
	
	
}
