package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ChallengeDayTwo {	
			
	public long solveChallenge(List<String> lines, String bagContent) {		
		Subset bag = createSubset(bagContent); 
		List<Game> games = parseLines(lines);
		List<Game> validGames = validateGames(games, bag); 
		return sumValidLines(validGames);
	}

	private List<Game> parseLines(List<String> lines) {
		List<Game> games = new ArrayList<>();
		for (String line : lines) {
			String[] array = line.split(":"); //TODO rename
			String gameString = array[0];
			Game game = parseGame(gameString);
			String subsets = array[1];
			List<String> subsetsList = new ArrayList<String>(Arrays.asList(subsets.split(";")));
			List<Subset> subsets1 = parseSubsets(subsetsList);
			
			game.setSubsets(subsets1);
			
			
			games.add(game);
		}
		
		return games;
	}

	protected Game parseGame(String string) {
		String trim = string.replace("Game", "").trim();
		return new Game(Integer.valueOf(trim));
	}
	
	protected List<Subset> parseSubsets(List<String> list) {
		List<Subset> subsets = new ArrayList<>();
		for ( String s : list) {
			subsets.add(createSubset(s));
		}
	return subsets;
	}
		
	protected Subset createSubset(String string) {
		List<String> cubes = new ArrayList<String>(Arrays.asList(string.split(",")));
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for (String cube : cubes) {
			if (cube.contains("red")) {
				String trim = cube.replace("red", "").trim();
				red = Integer.valueOf(trim);
			}
			
			if (cube.contains("green")) {
				String trim = cube.replace("green", "").trim();
				green = Integer.valueOf(trim);
			}
			
			if (cube.contains("blue")) {
				String trim = cube.replace("blue", "").trim();
				blue = Integer.valueOf(trim);
			}
		}
		return new Subset(red, green, blue);
	}
	
	
	protected long sumValidLines(List<Game> validGames) {
		return validGames.stream().
				map(Game::getId).
				reduce(0,Integer::sum);
	}

	protected List<Game> validateGames(List<Game> games, Subset bag) {
		List<Game> validGames = new ArrayList<>();		
		for (Game game : games) {
			boolean subsetsValid = true;
			if (game.getSubsets().isEmpty()) {
				subsetsValid = false;
			}
			for (Subset subset : game.getSubsets()) {
				boolean impossibleSubset = bag.getRed() < subset.getRed() || bag.getGreen() < subset.getGreen() || bag.getBlue() < subset.getBlue();
				if (impossibleSubset) {
					subsetsValid = false;
				}
			}
			if (subsetsValid) {
				validGames.add(game);
			}
		}
		return validGames;
	}	
	
	public Game createGame(String gameString, List<String> subsets) {
		Game game = parseGame(gameString);
		List<Subset> parseSubsets = parseSubsets(subsets);
		game.setSubsets(parseSubsets);
		return game;
	}
	
	public Game createGame(int id) {
		return new Game(id);
	}
	

	@Getter
	protected class Game {
		
		public Game(int id) {
			this.id = id;
		}
		
		private int id;
	
		@Setter
		private List<Subset> subsets;
		
	}
	
	@Getter
	protected class Subset {
		
		public Subset(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		private int red;
		
		private int green;
		
		private int blue;
		
	}
	
}
