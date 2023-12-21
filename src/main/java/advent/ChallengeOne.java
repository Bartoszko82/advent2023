package advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChallengeOne {

	private InputReader inputReader;
	
	
	private final Function<String, String> getLineSum = s -> {
		
		List<String> ints = new ArrayList<>();
		
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (Character.isDigit(c)) {			
				ints.add(String.valueOf(c));
			}	
		}
		return ints.isEmpty() ? "0" : ints.get(0) + ints.get(ints.size() -1 );
	};

	protected long sumLines(Stream<String> lines) {
		if (lines == null) {
			return 0;
		}
		return lines.map(getLineSum::apply)
				.map(Integer::valueOf)
	    		.collect(Collectors.summarizingInt(Integer::intValue)).getSum();	
	}
	
	
	public long solveChallenge() throws IOException {		
		Stream<String> lines = inputReader.readInput("src/main/resources/ChallengeOne");
		return sumLines(lines);
}
	
	
}
