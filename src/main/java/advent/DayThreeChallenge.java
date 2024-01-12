package advent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class DayThreeChallenge {

	public long solveChallenge(List<String> lines) {	
		Map<Integer, List<Element>> elements = parseElements(lines);
		Map<Integer, List<Element>> engineParts = filterEngineParts(elements);
		return sumEngineParts(engineParts);
	}
	
	protected long sumEngineParts(Map<Integer, List<Element>> engineParts) {
		int sum = 0;
	for (Entry<Integer, List<Element>> elements : engineParts.entrySet()) {
		sum += elements.getValue().stream().map(e -> e.element).mapToInt(s -> Integer.valueOf(s)).sum();
	}
		return sum;
	}
	
	protected Map<Integer, List<Element>> filterEngineParts(Map<Integer, List<Element>> elements) {
		Map<Integer, List<Element>> parts = new HashMap<>();
		
		Set<Entry<Integer, List<Element>>> entrySet = elements.entrySet();
		
				
		for (Entry<Integer, List<Element>> entry : entrySet) {
				
			parts.put(entry.getKey(), new ArrayList<>());
			
			List<Element> value = entry.getValue();
			
			for (Element el : value) {
				
				if (el.isEnginePart) {
					System.out.println("el: " + el.getElement());
					int begging = el.getStartIndex();
					int end = el.getEndIndex();
				
					for (Element el2 : value) {
						
						if (!el2.isEnginePart) {
							System.out.println("el2: " + el2.getElement());
							int begging2 = el2.getStartIndex();
							int end2 = el2.getEndIndex();
					
							
							if (true) {
							
							List<Element> list = parts.get(entry.getKey());
							list.add(el);
							
							System.out.println("adding part: " + el.getElement());
							
							}
						}	
						
					}
				
				}
			}
		
		}
		return parts;
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
	
}

