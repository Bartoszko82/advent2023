package advent;

import java.io.IOException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
				
		InputReader inputReader = new InputReader();
		
		ChallengeDayOne one = new ChallengeDayOne(new InputReader());
		long resultOne = one.solveChallenge();
		System.out.println("ChallengeOne result: " + resultOne);
		
		List<String> challengeTwoInput = inputReader.readInput("src/main/resources/ChallengeDayTwoInput");
		ChallengeDayTwo two = new ChallengeDayTwo();
		String bagContent = "12 red, 13 green, 14 blue";
		long resultTwo = two.solveChallenge(challengeTwoInput, bagContent);
		System.out.println("ChallengeTwo result: " + resultTwo);
		
		long resultTwoPartTwo = two.solveChallengePartTwo(challengeTwoInput);
		System.out.println("ChallengeTwoPartTwo result: " + resultTwoPartTwo);

	}
}
