package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import advent.DayThreeChallenge.Element;

public class DayThreeChallengeTest {

	DayThreeChallenge challenge;
	
	List<String> testInput = Arrays.asList("467..114..",
										   "...*......",
										   "..35..633.",
										   "......#...",
										   "617*......",
										   ".....+.58.",
										   "..592.....",
										   "......755.",
										   "...$.*....",
										   ".664.598..");
	@Before
	public void setUp() {
		challenge = new DayThreeChallenge();
	}
	
	@Test
	public void testExample() {
		long result = challenge.solveChallenge(testInput);
		Assert.assertTrue(result == 4361);
	}	

	@Test
	public void testProcessingLineWithEnginePart() {
		List<Element> processedLine = challenge.processLine("..456..");
		Element element = processedLine.get(0);
		Assert.assertTrue(element.isEnginePart);
		Assert.assertEquals(3, element.getStartIndex());
		Assert.assertEquals(5, element.getEndIndex());
		Assert.assertEquals("456", element.getElement());	
	}
	
	@Test
	public void testProcessingLineWithSymbol() {
		List<Element> processedLine = challenge.processLine("..@..");
		Element element = processedLine.get(0);
		Assert.assertFalse(element.isEnginePart);
		Assert.assertEquals(3, element.getStartIndex());
		Assert.assertEquals(3, element.getEndIndex());
		Assert.assertEquals("@", element.getElement());	
	}
	
	@Test
	public void testParsingLines() {
		Map<Integer, List<Element>> parts = challenge.parseLinesToMapOfElements(Arrays.asList("..456....123....", ".12....158"));
		Map<Integer, List<Element>> symbols = challenge.parseLinesToMapOfElements(Arrays.asList(".&....#....@"));
		Map<Integer, List<Element>> mixed = challenge.parseLinesToMapOfElements(Arrays.asList(".&45....@"));
		Map<Integer, List<Element>> doubles = challenge.parseLinesToMapOfElements(Arrays.asList(".45....45....@..@"));
		Assert.assertTrue(parts.size() == 2);
		Assert.assertTrue(parts.get(0).size() == 2 && parts.get(1).size() == 2 );
		Assert.assertTrue(allEngineParts(parts.get(0)) && allEngineParts(parts.get(1)));		
		Assert.assertTrue(getValuesAsStrings(parts.get(0)).equals(Arrays.asList("456","123")));
		Assert.assertTrue(getValuesAsStrings(parts.get(1)).equals(Arrays.asList("12","158")));
		Assert.assertTrue(symbols.get(0).size() == 3);
		Assert.assertTrue(getValuesAsStrings(symbols.get(0)).equals(Arrays.asList("&","#","@")));
		Assert.assertTrue(mixed.get(0).size() == 3);
		Assert.assertTrue(getValuesAsStrings(mixed.get(0)).equals(Arrays.asList("&","45","@")));
		Assert.assertTrue(doubles.get(0).size() == 4);
		Assert.assertTrue(getValuesAsStrings(doubles.get(0)).equals(Arrays.asList("45","45","@","@")));
	}
	
	private List<String> getValuesAsStrings (List<Element> elements) {
		return elements.stream().map(e -> e.getElement()).collect(Collectors.toList());
	}
 	
	private boolean allEngineParts(List<Element> elements) {
		return elements.stream().allMatch(e -> e.isEnginePart);
	}
	
	@Test
	public void testCheckLine() {		
		List<Element> line = new ArrayList<>();
		Element firstSymbol = challenge.createElement("#", false, 1, 1);
		Element secondSymbol = challenge.createElement("@", false, 10, 10);
		Element thirdSymbol = challenge.createElement("&", false, 6, 6);
		line.add(firstSymbol);
		line.add(secondSymbol);
		boolean adjacentSymbol = challenge.checkLine(3,5, line);
		Assert.assertFalse(adjacentSymbol);

		line.add(thirdSymbol);
		boolean adjacentSymbol2 = challenge.checkLine(3,5, line);
		Assert.assertTrue(adjacentSymbol2);
		
		boolean adjacentSymbol3 = challenge.checkLine(3,8, line);
		Assert.assertTrue(adjacentSymbol3);
	}
		
	@Test
	public void testSumEngineParts() {
		Element firstElement = challenge.createElement("456", true, 1, 4);
		Element secondElement = challenge.createElement("1528", true, 2, 5);
		List<Element> engineParts = new ArrayList<>();
		engineParts.add(firstElement);
		engineParts.add(secondElement);
		long result = challenge.sumEngineParts(engineParts);
		Assert.assertTrue(result == 1984);
	}

	
}
