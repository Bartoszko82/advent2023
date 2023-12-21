package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class InputReader {
	
	protected Stream<String> readInput(String path) throws IOException {
		try (FileReader fileReader = new FileReader(path); BufferedReader reader = new BufferedReader(fileReader)) {
			return reader.lines();
		}
	}
}