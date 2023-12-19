package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ChallengeOne {
	
	public void readFile() throws IOException {
			
	Function<String, Integer> getNumbers = s -> s.length();

		
    String file ="src/main/resources/ChallengeOne";
    
    BufferedReader reader = new BufferedReader(new FileReader(file));
    
   reader.lines().map(getNumbers::apply)
    		.forEach(i -> System.out.print(i+ "\n"));
    
    reader.close();
	
}
}
