package advent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DayThreeChallenge {

	public long solveChallenge(List<String> lines) {	
		Map<Integer, List<Element>> linesOfElements = parseElements(lines);
		List<Element> engineParts = filterEngineParts(linesOfElements);
		return sumEngineParts(engineParts);
	}
	
//	protected long sumEngineParts(Map<Integer, List<Element>> engineParts) {
//		int sum = 0;
//	for (Entry<Integer, List<Element>> elements : engineParts.entrySet()) {
//		sum += elements.getValue().stream().map(e -> e.element).mapToInt(s -> Integer.valueOf(s)).sum();
//	}
//		return sum;
//	}

	protected long sumEngineParts(List<Element> engineParts) {
	return engineParts.stream()
			.map(Element::getElement)
			.map(Integer::valueOf)
			.reduce(0,Integer::sum);
	}
	
	protected List<Element> getEngineParts (TestLines testLines) {
		
		return testLines.getCurrentLine();
	}
	
	protected List<Element> filterEngineParts(Map<Integer, List<Element>> linesOfElements) {
		List<Element> listOfParts = new ArrayList<>();
		TestLines testLines = new TestLines();
		
		for (Entry<Integer, List<Element>> lineOfElements : linesOfElements.entrySet()) {
			List<Element> line = lineOfElements.getValue();
			testLines.addNextLine(line);
			List<Element> engineParts = getEngineParts(testLines); 
			listOfParts.addAll(engineParts);
		}
		return listOfParts;
	}
		
	protected Map<Integer, List<Element>> parseElements(List<String> lines) {
		Map<Integer, List<Element>> elements = new HashMap<>();
		for (String line : lines) {		
			elements.put(elements.size(), processLine(line));
		}
	return elements;	
	}
		
	private List<Element> processLine (String line) {
		List<Element> elementsInLine = new ArrayList<>();	
		for (String elementString : line.split("\\.")) {
			if (elementString.length() == 0) {
				continue;
			}
			Element element = newElementFromString(line, elementString);
			elementsInLine.add(element);
		}
		return elementsInLine;
	}
	
	private Element newElementFromString (String line, String elementString) {
		int startIndex = line.indexOf(elementString);
		int endIndex = startIndex + elementString.length();
		boolean isEnginePart = elementString.matches("[1-9]*");
		return createElement(elementString, isEnginePart, startIndex, endIndex);
	}
	
	protected Element createElement(String element, boolean isEnginePart, int startIndex, int endIndex) {
		return new Element(element, isEnginePart, startIndex, endIndex);
	}
	
	@Setter
	@Getter
	@AllArgsConstructor
	protected class Element {
		
		String element;
		
		boolean isEnginePart;
		
		int startIndex;
		
		int endIndex;	
	}
	
	@Getter
	@NoArgsConstructor
	protected class TestLines {

		List<Element> previousLine;
		
		List<Element> currentLine;
		
		List<Element> nextLine;
		
		public TestLines (List<Element> previousLine, List<Element> currentLine, List<Element> nextLine) {
			previousLine = previousLine != null ? previousLine : Collections.emptyList();
			currentLine = currentLine != null ? currentLine : Collections.emptyList();
			nextLine = nextLine != null ? nextLine : Collections.emptyList();
		}
		
		public void addNextLine(List<Element> newNextLine) {
			previousLine = currentLine;
			currentLine = nextLine;
			nextLine = newNextLine != null ? newNextLine : Collections.emptyList();
		}
	}
	
}

