package advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DayOneChallenge {

	private final InputReader inputReader;
	
	private final List<String> enumPatterns = convertEnumToPatterns();
	
	private final Function<String, Integer> getLineCalibrationValue = line -> {		
		Map<Integer, String> digitsMap = findAllDigitsInLine(line);
		if (digitsMap.isEmpty()) {
			return 0;
		}
		
		Set<Integer> keySet = digitsMap.keySet();
		int minIndex = Collections.min(keySet);
		int maxIndex = Collections.max(keySet);
		return Integer.valueOf(digitsMap.get(minIndex) + digitsMap.get(maxIndex));
	};
	
	private enum Digit {
		ONE("1"),TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8"),NINE("9");
		
	    public final String label;

	    private Digit(String label) {
	        this.label = label;
	    }
	}
			
	public long solveChallenge() throws IOException {		
		List<String> lines = inputReader.readInput("src/main/resources/ChallengeDayOneInput");
		return sumLines(lines);
	}

	protected long sumLines(List<String> lines) {
		if (lines == null || lines.isEmpty()) {
			return 0;
		};	
	
		return lines.stream()
				.map(getLineCalibrationValue::apply)
				.reduce(0, Integer::sum);
	}
	
	private List<String> convertEnumToPatterns() {
		List<String> values = new ArrayList<>();
		for (Digit digit : Digit.values()) {
			values.add(digit.name().toLowerCase());
		}
		return values;
	}
		
	private Map<Integer, String> findAllDigitsInLine(String line) {
		Map<Integer, String> result = new HashMap<>();
		List<String> patterns = new ArrayList<>();
		patterns.add("[1-9]");
		patterns.addAll(enumPatterns);
		for (String pattern : patterns) {
			findDigits(line, pattern, result);
		}
		return result;
	}
	
	private void findDigits (String line, String patternString, Map<Integer,String> result) {
		Pattern pattern = Pattern.compile(patternString);
	    Matcher matcher = pattern.matcher(line);
	    while (matcher.find()) {
	        String match = matcher.group();
	        String digit = enumPatterns.contains(match) ? Digit.valueOf(match.toUpperCase()).label : match ;
	        result.put(matcher.start(), digit);
	    }
		
	}
	
}
