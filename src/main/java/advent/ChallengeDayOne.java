package advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChallengeDayOne {

	private InputReader inputReader;
	
	private enum Digit {
		ONE("1"), TWO("2"), THREE("3"), FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8"),NINE("9");
		
	    public final String label;

	    private Digit(String label) {
	        this.label = label;
	    }
	}
	
	private final Function<String, String> getLineCalibrationValue = line -> {		
		Map<Integer, String> result = new HashMap<>();
		
		for (Digit digit : Digit.values()) {	
			int indexOfName = line.indexOf(digit.toString().toLowerCase());
			if (indexOfName >= 0) {
				result.put(indexOfName, digit.label);
			}
			int indexOfLabel = line.indexOf(digit.label);
			
			if (indexOfLabel >= 0) {
				result.put(indexOfLabel, digit.label);
			}
		}
			
			Set<Integer> keySet = result.keySet();
			if (keySet.isEmpty()) {
				return "0";
			}
			int minIndex = Collections.min(keySet);
			int maxIndex = Collections.max(keySet);
			
			return result.get(minIndex) + result.get(maxIndex);
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
