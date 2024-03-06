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
	
	public long solveChallengeSecondPart(List<String> lines) {	
		Map<Integer, List<Element>> linesOfElements = parseLinesToMapOfElements(lines);
		List<Integer> gearRatios = findGearRatios(linesOfElements);
		return gearRatios.stream().reduce(Integer::sum).orElse(0);
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
				boolean hasAdjacentElement = checkLinesForEngineParts(element, testLines);
				if (hasAdjacentElement) {
					engineParts.add(element);
				}	
			}
		}
		return engineParts;
	}
	
	protected List<Integer> findGearRatios(Map<Integer, List<Element>> linesOfElements) {
		List<Integer> listOfGearRatios = new ArrayList<>();
		TestLines testLines = new TestLines(Collections.emptyList(), Collections.emptyList(), linesOfElements.get(0));
		for (Entry<Integer, List<Element>> lineOfElements : linesOfElements.entrySet()) {
			List<Element> line = lineOfElements.getValue();
			testLines.addNextLine(line);
			List<Integer> gearRatios = getGearsRatios(testLines);
			//TODO BZ
			listOfGearRatios.addAll(gearRatios);
		}		

		return listOfGearRatios;
	}
	

	protected List<Integer> getGearsRatios (TestLines testLines) {
		List<Integer> gearRatios = new ArrayList<>();
		List<Element> currentLine = testLines.getCurrentLine();
		if (currentLine == null || currentLine.isEmpty()) {
			return Collections.emptyList();
		}
		for (Element element : currentLine) {
			if ("*".equals(element.getElement())) {
				List<Integer> gearInPrevLine = checkLineForRatioElement(element, testLines.getPreviousLine());
				List<Integer> gearInCurrLine = checkLineForRatioElement(element, testLines.getCurrentLine());
				List<Integer> gearInNextLine = checkLineForRatioElement(element, testLines.getNextLine());
				gearInPrevLine.addAll(gearInCurrLine);
				gearInPrevLine.addAll(gearInNextLine);
				
				if (gearInPrevLine.size() == 2) {
					gearRatios.add(gearInPrevLine.get(0) * gearInPrevLine.get(1));
				} 
			}
		}
		return gearRatios;
	}

	
	protected boolean checkLinesForEngineParts(Element checkedElement, TestLines testLines) {
		boolean adjacentSymbolPrev = checkLine(checkedElement, testLines.getPreviousLine());
		boolean adjacentSymbolCurr = checkLine(checkedElement, testLines.getCurrentLine());
		boolean adjacentSymbolNext = checkLine(checkedElement, testLines.getNextLine());
		return adjacentSymbolPrev || adjacentSymbolCurr || adjacentSymbolNext;
	}
	
	protected boolean checkLine(Element checkedElement, List<Element> line) {
		if (line.isEmpty()) {
			return false;
		}
		for (Element element : line) {
			if (!element.isEnginePart) {
				int symbolPos = element.getStartIndex();
				for (int i = checkedElement.getStartIndex() - 1 ; i <= checkedElement.getEndIndex() + 1 ; i++ ) {
					if (symbolPos == i) {
						return true;
					}
				}
			}	
		}	
		return false;
	}
	
	protected List<Integer> checkLineForRatioElement(Element checkedElement, List<Element> line) {
		List<Integer> list = new ArrayList<>();
		if (line.isEmpty()) {
			return list;
		}
		for (Element element : line) {
			if (element.isEnginePart) {
				int symbolPos = checkedElement.getStartIndex();
				for (int i = element.getStartIndex() - 1 ; i <= element.getEndIndex() + 1 ; i++ ) {
					if (symbolPos == i) {
						list.add(Integer.parseInt(element.getElement()));
					}
				}
			}	
		}	
		return list;
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

