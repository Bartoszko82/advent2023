package advent;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;

import java.util.stream.Stream;

import org.junit.Test;

public class ChallengeOneTest {

	
	ChallengeOne challenge;
	
	@Before
	public void setUp() {
		challenge = new ChallengeOne(new InputReader());
	}
	
	
	@Test
	public void testSingleLine() {		
		Stream<String> testInput = Stream.of("a5aaaa2a1aaaa");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 51);
	}


	@Test
	public void testMultipleLines() {
		Stream<String> testInput = Stream.of("a1aaaa2", "b2bbb6bbbb5", "c2ccc");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 59);
	}
	
	@Test
	public void testEmptyLines() {
		Stream<String> testInput = Stream.of("");
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 0);
	}
	
	@Test
	public void testNoLines() {
		Stream<String> testInput = null;
		long result = challenge.sumLines(testInput);
		Assert.assertTrue(result == 0);
	}
}
