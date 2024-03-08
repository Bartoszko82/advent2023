package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DayFourChallenge {

	private static final String NUMBERS_SEPARATOR = " ";
	
	private static final String PARTS_SEPARATOR = "|";
	
	public long solveChallenge(List<String> lines) {	
		Set<Scratchcard> scratchcards = parseLinesToScratchcards(lines);
		return 1L;
	}
	
	

	protected Set<Scratchcard> parseLinesToScratchcards(List<String> lines) {
		return lines.stream().map(line -> parseLine(line)).collect(Collectors.toSet());
		
	}
	
	protected Scratchcard parseLine(String line) {
		
		int cardNumber = Integer.valueOf(line.substring(5,8).trim());
		String[] linePart = line.split(PARTS_SEPARATOR);
		List<Integer> winningNumbers = stringToIntegers(linePart[0]);
		List<Integer> userNumbers = stringToIntegers(linePart[1]);
		return new Scratchcard (cardNumber, winningNumbers, userNumbers);
	}
	
	protected List<Integer> stringToIntegers (String linePart) {
		String[] numbersArray = linePart.split(NUMBERS_SEPARATOR);
		List<String> numbers = Arrays.asList(numbersArray);
		return numbers.stream().map(String::trim).map(Integer::valueOf).collect(Collectors.toList());			
	}

		
	@Setter
	@Getter
	@AllArgsConstructor
	protected class Scratchcard {
		
		Integer cardNumber;
		
		List<Integer> winningNumbers;
		
		List<Integer> playernumbers;
		
	
	}
	
	
	
}

