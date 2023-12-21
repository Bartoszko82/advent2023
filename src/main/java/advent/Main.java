package advent;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		ChallengeOne one = new ChallengeOne(new InputReader());
		long resultOne = one.solveChallenge();
		System.out.print("ChallengeOne result: " + resultOne);
		
		
	}

}
