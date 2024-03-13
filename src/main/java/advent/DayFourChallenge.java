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
	
	public int solveChallenge(List<String> lines) {	
		Set<Scratchcard> scratchcards = parseLinesToScratchcards(lines);
		return scratchcards.stream()
			.map(scratchcard -> getMatchingNumbers(scratchcard))
			.map(list -> getScratchcardValue(list))
			.reduce(0,Integer::sum);
	}
	
	protected List<Integer> getMatchingNumbers(Scratchcard scratchcard) {
		List<Integer> winningNumbers = scratchcard.getWinningNumbers();
		List<Integer> playerNumbers = scratchcard.getPlayerNumbers();
		return playerNumbers.stream()
				.filter(number -> winningNumbers.contains(number))
				.collect(Collectors.toList());
	}
	
	protected Integer getScratchcardValue(List<Integer> matchingNumbers) {
		int x = 0;
		for (int i = 0; i < matchingNumbers.size(); i++) {
			x = x * 2;
			x = Math.max(1, x);
		}
		return x;
	}

	protected Set<Scratchcard> parseLinesToScratchcards(List<String> lines) {
		return lines.stream()
				.map(line -> parseLine(line))
				.collect(Collectors.toSet());
	}
	
	protected Scratchcard parseLine(String line) {
		String[] lineParts = line.split(CARD_SEPARATOR);
		int cardNumber = linePartToCardNumber(lineParts[0]);
		String[] numberParts = lineParts[1].split(PARTS_SEPARATOR);
		List<Integer> winningNumbers = linePartToNumbers(numberParts[0]);
		List<Integer> playerNumbers = linePartToNumbers(numberParts[1]);
		return new Scratchcard (cardNumber, winningNumbers, playerNumbers);
	}
	
	protected int linePartToCardNumber (String linePart) {
		String numberAsString = linePart.replace("Card", " ");
		return Integer.valueOf(numberAsString.trim());
	}
	
	protected List<Integer> linePartToNumbers (String linePart) {
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
		
		List<Integer> playerNumbers;
		
	
	}
	
	
	
}

