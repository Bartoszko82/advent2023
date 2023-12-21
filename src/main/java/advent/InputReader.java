package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {
	
	protected List<String> readInput(String path) throws IOException {
		
		try (FileReader fileReader = new FileReader(path); BufferedReader reader = new BufferedReader(fileReader)) {
			return reader.lines().collect(Collectors.toList());
		}
	}
}