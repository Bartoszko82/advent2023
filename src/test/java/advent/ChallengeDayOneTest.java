package advent;

import org.junit.Assert;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChallengeDayOneTest {

	
	ChallengeDayOne challenge;
	
	@Before
	public void setUp() {
		challenge = new ChallengeDayOne(new InputReader());
	}
	
	
	@Test
	public void testSimpleLine() {		
		List<String> testInput = Arrays.asList("a5aa1aa2a1aaaa");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 51);
	}

	@Test
	public void testComplicatedLine() {		
		List<String> testInput = Arrays.asList("ddgjgcrssevensix37twooneightgt");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 78);
	}
	
	@Test
	public void testMultipleLines() {
		List<String> testInput = Arrays.asList("a1aaaa2", "b2bbb6bbbb5", "c2ccc");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 59);
	}
	
	@Test
	public void testExample() {
		List<String> testInput = Arrays.asList("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f","treb7uchet");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 142);
	}	
	
	@Test
	public void testSecondExample() {
		List<String> testInput = Arrays.asList("two1nine", "eightwothree", "abcone2threexyz","xtwone3four","4nineeightseven2", "zoneight234","7pqrstsixteen");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 281);
	}	
	
	@Test
	public void testEmptyLines() {
		List<String> testInput = Arrays.asList("");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 0);
	}
	
	@Test
	public void testNoLines() {
		List<String> testInput = null;
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 0);
	}
}
