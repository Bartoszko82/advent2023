package advent;

import java.io.IOException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
				
		InputReader inputReader = new InputReader();
		
		DayOneChallenge dayOneChallenge = new DayOneChallenge(inputReader);
		long dayOneResult = dayOneChallenge.solveChallenge();
		System.out.println("Day one challenge result: " + dayOneResult);
		
		List<String> dayTwoChallengeInput = inputReader.readInput("src/main/resources/DayTwoChallengeInput");
		DayTwoChallenge dayTwoChallenge = new DayTwoChallenge();
		String bagContent = "12 red, 13 green, 14 blue";
		long dayTwoResult = dayTwoChallenge.solveChallenge(dayTwoChallengeInput, bagContent);
		System.out.println("Day two challenge result: " + dayTwoResult);
		
		long dayTwoResultSecondPart = dayTwoChallenge.solveChallengeSecondPart(dayTwoChallengeInput);
		System.out.println("Day two challenge second part result: " + dayTwoResultSecondPart);

	}
}
