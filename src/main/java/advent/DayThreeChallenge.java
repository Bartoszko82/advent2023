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
		Map<Integer, List<Element>> linesOfElements = parseLinesToMapOfElements(lines);
		List<Element> engineParts = filterEngineParts(linesOfElements);
		return sumEngineParts(engineParts);
	}

	protected Map<Integer, List<Element>> parseLinesToMapOfElements(List<String> lines) {
		Map<Integer, List<Element>> elements = new HashMap<>();
		for (String line : lines) {		
			elements.put(elements.size(), processLine(line));
		}
		return elements;	
	}

	List<Element> processLine (String line) {
		List<Element> elementsInLine = new ArrayList<>();
		String tempPart = "";
		int pos = 1;
		for (char elementChar : line.toCharArray()) {
			String elementString = Character.toString(elementChar);
			if (elementString.matches("[0-9]")) {
				tempPart += elementString;
				pos++;
				continue;
			} else { 
				if (!tempPart.isEmpty()) {
					Element enginePart = createElement(tempPart, true, pos - tempPart.length(), pos - 1);
					tempPart = "";
					elementsInLine.add(enginePart);
				}
				if (!elementString.equals(".")) {
					Element symbol = createElement(elementString, false, pos, pos);	
					elementsInLine.add(symbol);
				}
				pos++;
			}
		}
		if (!tempPart.isEmpty()) {
			Element enginePart = createElement(tempPart, true, pos - tempPart.length(), pos - 1);
			elementsInLine.add(enginePart);
		}
		return elementsInLine;
	}

	protected List<Element> filterEngineParts(Map<Integer, List<Element>> linesOfElements) {
		List<Element> listOfParts = new ArrayList<>();
		TestLines testLines = new TestLines(Collections.emptyList(), linesOfElements.get(0), linesOfElements.get(1));
		for (Entry<Integer, List<Element>> lineOfElements : linesOfElements.entrySet()) {
			List<Element> line = lineOfElements.getValue();
			testLines.addNextLine(line);
			List<Element> engineParts = getEngineParts(testLines);
			listOfParts.addAll(engineParts);
		}		
		testLines.addNextLine(Collections.emptyList());
		List<Element> engineParts = getEngineParts(testLines); 
		listOfParts.addAll(engineParts);
		return listOfParts;
	}

	protected List<Element> getEngineParts (TestLines testLines) {
		List<Element> engineParts = new ArrayList<>();
		List<Element> currentLine = testLines.getCurrentLine();
		if (currentLine == null || currentLine.isEmpty()) {
			return Collections.emptyList();
		}
		for (Element element : currentLine) {
			if (element.isEnginePart()) {
				int startIndex = element.getStartIndex();
				int endIndex = element.getEndIndex();
				boolean hasAdjacentElement = checkLines(startIndex, endIndex, testLines);
				if (hasAdjacentElement) {
					engineParts.add(element);
				}	
			}
		}
		return engineParts;
	}
	
	protected boolean checkLines(int startIndex, int endIndex, TestLines testLines) {
		boolean adjacentSymbolPrev = checkLine(startIndex, endIndex, testLines.getPreviousLine());
		boolean adjacentSymbolCurr = checkLine(startIndex, endIndex, testLines.getCurrentLine());
		boolean adjacentSymbolNext = checkLine(startIndex, endIndex, testLines.getNextLine());
		return adjacentSymbolPrev || adjacentSymbolCurr || adjacentSymbolNext;
	}
	
	protected boolean checkLine(int startIndex, int endIndex, List<Element> line) {
		if (line.isEmpty()) {
			return false;
		}
		for (Element element : line) {
			if (!element.isEnginePart) {
				int symbolPos = element.getStartIndex();
				for (int i = startIndex - 1 ; i <= endIndex + 1 ; i++ ) {
					if (symbolPos == i) {
						return true;
					}
				}
			}	
		}	
		return false;
	}
	
	protected long sumEngineParts(List<Element> engineParts) {
	return engineParts.stream()
			.map(Element::getElement)
			.map(Integer::valueOf)
			.reduce(0,Integer::sum);
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
			this.previousLine = previousLine != null ? previousLine : Collections.emptyList();
			this.currentLine = currentLine != null ? currentLine : Collections.emptyList();
			this.nextLine = nextLine != null ? nextLine : Collections.emptyList();
		}
		
		public void addNextLine(List<Element> nextLine) {
			this.previousLine = this.currentLine;
			this.currentLine = this.nextLine;
			this.nextLine = nextLine != null ? nextLine : Collections.emptyList();
		}
	}
	
}

