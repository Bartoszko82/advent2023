package advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChallengeDayOne {

	private InputReader inputReader;
	
	private final Function<String, String> getLineCalibrationValue = s -> {		
		List<String> digits = new ArrayList<>();
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (Character.isDigit(c)) {			
				digits.add(String.valueOf(c));
			}	
		}
		return digits.isEmpty() ? "0" : digits.get(0) + digits.get(digits.size()-1);
	};


	public long solveChallenge() throws IOException {		
		List<String> lines = inputReader.readInput("src/main/resources/ChallengeDayOneInput");
		return sumLines(lines);
	}

	protected long sumLines(List<String> lines) {
		if (lines == null || lines.isEmpty()) {
			return 0;
		}
		return lines.stream()
				.map(getLineCalibrationValue::apply)
				.map(Integer::valueOf)
	    		.collect(Collectors.summarizingInt(Integer::intValue)).getSum();	
	}
	
}
