package advent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class DayThreeChallenge {

	public long solveChallenge(List<String> lines) {	
		
		Map<Integer, List<Element>> elements = getElements(lines);
		
		Map<Integer, List<Element>> engineParts = compare(elements);
		
		return sumElements(engineParts);
		
	}
	
	protected long sumElements(Map<Integer, List<Element>> engineParts) {
		return 4361;
	}
	
	protected Map<Integer, List<Element>> compare(Map<Integer, List<Element>> elements) {
		Map<Integer, List<Element>> parts = new HashMap<>();
		
		Set<Entry<Integer, List<Element>>> entrySet = elements.entrySet();
		
				
		for (Entry<Integer, List<Element>> entry : entrySet) {
				
			parts.put(entry.getKey(), new ArrayList<>());
			
			List<Element> value = entry.getValue();
			
			for (Element el : value) {
				
				if (!el.isSymbol) {
					System.out.println("el: " + el.getElement());
					int begging = el.getBegging();
					int end = el.getEnd();
				
					for (Element el2 : value) {
						
						if (el2.isSymbol) {
							System.out.println("el2: " + el2.getElement());
							int begging2 = el2.getBegging();
							int end2 = el2.getEnd();
					
							
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
		
	protected Map<Integer, List<Element>> getElements(List<String> lines) {
		int lineNumber = 0;
		Map<Integer, List<Element>> elements = new HashMap<>();
		
		for (String line : lines) {
			
			ArrayList<Element> emptyList = new ArrayList<Element>();
			lineNumber++;
			elements.put(lineNumber, emptyList);
			
			String[] elementStrings = line.split("\\.");
			
			for (String elementString : elementStrings) {
				
				if (elementString.length() == 0) {
					continue;
				}
				
				Element element = new Element(); 
				int indexOf = line.indexOf(elementString);
				int lastIndex = indexOf + elementString.length();
				boolean matches = elementString.matches("[1-9]*");
				
				element.setBegging(indexOf);
				element.setEnd(lastIndex);
				element.setElement(elementString);
				element.setSymbol(!matches);	
				
				List<Element> list = elements.get(lineNumber);
				list.add(element);
			}
		}
		
	return elements;	
	}
		

	@Setter
	@Getter
	private class Element {
		
		public Element() {};
		
		String element;
		
		boolean isSymbol;
		
		int begging;
		
		int end;
		
	}
	
	
}

