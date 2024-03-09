package advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class DayFourChallenge {

	private static final String CARD_SEPARATOR = ":";
	
	private static final String PARTS_SEPARATOR = "\\|";
	
	private static final String NUMBERS_SEPARATOR = "\\s+";
	
	public long solveChallenge(List<String> lines) {	
		Set<Scratchcard> scratchcards = parseLinesToScratchcards(lines);
		return 1L;
	}
	
	

	protected Set<Scratchcard> parseLinesToScratchcards(List<String> lines) {
		return lines.stream().map(line -> parseLine(line)).collect(Collectors.toSet());
		
	}
	
	protected Scratchcard parseLine(String line) {
		String[] lineParts = line.split(CARD_SEPARATOR);
		int cardNumber = lineToCardNumber(lineParts[0]);
		String[] numberParts = lineParts[1].split(PARTS_SEPARATOR);
		List<Integer> winningNumbers = lineToNumbers(numberParts[0]);
		List<Integer> userNumbers = lineToNumbers(numberParts[1]);
		return new Scratchcard (cardNumber, winningNumbers, userNumbers);
	}
	
	protected int lineToCardNumber (String linePart) {
		String justNumber = linePart.replace("Card", " ");
		return Integer.valueOf(justNumber.trim());
	}
	
	protected List<Integer> lineToNumbers (String linePart) {
		String[] numbersArray = linePart.trim().split(NUMBERS_SEPARATOR);
		List<String> numbers = Arrays.asList(numbersArray);
		return numbers.stream()
				.map(String::trim)
				.map(Integer::valueOf)
				.collect(Collectors.toList());			
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

