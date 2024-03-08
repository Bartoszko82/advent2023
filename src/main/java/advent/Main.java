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

		
		
		List<String> dayThreeChallengeInput = inputReader.readInput("src/main/resources/DayThreeChallengeInput");
		DayThreeChallenge dayThreeChallenge = new DayThreeChallenge();
		long dayThreeResult = dayThreeChallenge.solveChallenge(dayThreeChallengeInput);
		System.out.println("Day three challenge result: " + dayThreeResult);
		
		long dayThreeResultSecondPart = dayThreeChallenge.solveChallengeSecondPart(dayThreeChallengeInput);
		System.out.println("Day three challenge second part result: " + dayThreeResultSecondPart);
		
		
		
		List<String> dayFourChallengeInput = inputReader.readInput("src/main/resources/DayFourChallengeInput");
		DayFourChallenge dayFourChallenge = new DayFourChallenge();
		long dayFourResult = dayFourChallenge.solveChallenge(dayFourChallengeInput);
		System.out.println("Day four challenge result: " + dayFourResult);
		
	}
}
