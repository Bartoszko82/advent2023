package advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class DayTwoChallenge {	
			
	public long solveChallenge(List<String> lines, String bagContent) {		
		CubesSet bag = createCubesSet(bagContent); 
		List<CubesGame> games = parseLinesToGames(lines);
		List<CubesGame> validGames = validateCubesGames(games, bag); 
		return sumValidLines(validGames);
	}

	public long solveChallengeSecondPart(List<String> lines) {		
		List<CubesGame> cubeGames = parseLinesToGames(lines);
		List<CubesSet> minimalCubesSets = getMinimalCubesSets(cubeGames); 
		return sumPowerOfCubesSets(minimalCubesSets);
	}

	protected List<CubesGame> validateCubesGames(List<CubesGame> cubesGames, CubesSet bag) {
		List<CubesGame> validCubesGames = new ArrayList<>();		
		for (CubesGame cubeGame : cubesGames) {
			boolean cubesSetValid = true;
			if (cubeGame.getCubesSets().isEmpty()) {
				cubesSetValid = false;
			}
			for (CubesSet cubesSet : cubeGame.getCubesSets()) {
				boolean impossibleCubesSet = bag.getRedCubes() < cubesSet.getRedCubes() 
						|| bag.getGreenCubes() < cubesSet.getGreenCubes() 
						|| bag.getBlueCubes() < cubesSet.getBlueCubes();
				if (impossibleCubesSet) {
					cubesSetValid = false;
				}
			}
			if (cubesSetValid) {
				validCubesGames.add(cubeGame);
			}
		}
		return validCubesGames;
	}	
	
	protected long sumValidLines(List<CubesGame> validCubesGames) {
		return validCubesGames.stream().
				map(CubesGame::getId).
				reduce(0,Integer::sum);
	}
	
	protected List<CubesSet> getMinimalCubesSets(List<CubesGame> cubesGames) {
		List<CubesSet> cubesSets = new ArrayList<>();
			for (CubesGame cubesGame : cubesGames) {
				cubesSets.add(getMinimalCubesSet(cubesGame.getCubesSets()));
			}
		return cubesSets;
	}
	
	private CubesSet getMinimalCubesSet(List<CubesSet> cubesSets) {
		int minRedCubes = 0;
		int minGreenCubes = 0;
		int minBlueCubes = 0;
		for (CubesSet cubesSet : cubesSets) {
			int redCubes = cubesSet.getRedCubes();
			minRedCubes = redCubes > minRedCubes ? redCubes : minRedCubes;
			int greenCubes = cubesSet.getGreenCubes();
			minGreenCubes = greenCubes > minGreenCubes ? greenCubes : minGreenCubes;
			int blueCubes = cubesSet.getBlueCubes();
			minBlueCubes = blueCubes > minBlueCubes ? blueCubes : minBlueCubes;
		}
		return new CubesSet(minRedCubes, minGreenCubes, minBlueCubes);
	}	
	
	protected long sumPowerOfCubesSets(List<CubesSet> minimalCubes) {
		return minimalCubes.stream().
				map(cubesSet -> cubesSet.getRedCubes() * cubesSet.getGreenCubes() * cubesSet.getBlueCubes()).
				reduce(0,Integer::sum);	
	}
	
	private List<CubesGame> parseLinesToGames(List<String> linesString) {
		List<CubesGame> cubesGames = new ArrayList<>();
		for (String line : linesString) {
			String[] lineParts = line.split(":");
			String gameString = lineParts[0];
			CubesGame cubesGame = parseStringToCubesGame(gameString);
			String cubesSetsPart = lineParts[1];
			List<String> cubesSetsStrings = new ArrayList<String>(Arrays.asList(cubesSetsPart.split(";")));
			List<CubesSet> cubesSets = parseStringsToCubeSets(cubesSetsStrings);
			
			cubesGame.setCubesSets(cubesSets);	
			cubesGames.add(cubesGame);
		}
		return cubesGames;
	}

	protected CubesGame parseStringToCubesGame(String gameString) {
		String trimmed = gameString.replace("Game", "").trim();
		return new CubesGame(Integer.valueOf(trimmed));
	}
	
	protected List<CubesSet> parseStringsToCubeSets(List<String> cubesSetsStrings) {
		List<CubesSet> cubesSet = new ArrayList<>();
		for ( String cubesSetString : cubesSetsStrings) {
			cubesSet.add(createCubesSet(cubesSetString));
		}
	return cubesSet;
	}
		
	protected CubesSet createCubesSet(String cubesSetString) {
		List<String> sets = new ArrayList<>(Arrays.asList(cubesSetString.split(",")));

		int redCubes = 0;
		int greenCubes = 0;
		int blueCubes = 0;
		
		for (String set : sets) {
			if (set.contains("red")) {
				String trimmed = set.replace("red", "").trim();
				redCubes = Integer.valueOf(trimmed);
			}
			
			if (set.contains("green")) {
				String trimmed = set.replace("green", "").trim();
				greenCubes = Integer.valueOf(trimmed);
			}
			
			if (set.contains("blue")) {
				String trimmed = set.replace("blue", "").trim();
				blueCubes = Integer.valueOf(trimmed);
			}
		}
		return new CubesSet(redCubes, greenCubes, blueCubes);
	}
	
	public CubesGame createCubesGame(String cubesGameString, List<String> cubesSetsStrings) {
		CubesGame cubeGame = parseStringToCubesGame(cubesGameString);
		List<CubesSet> cubesSets = parseStringsToCubeSets(cubesSetsStrings);
		cubeGame.setCubesSets(cubesSets);
		return cubeGame;
	}
	
	public CubesGame createCubesGame(int id) {
		return new CubesGame(id);
	}
	
	@Getter
	protected class CubesGame {
		
		public CubesGame(int id) {
			this.id = id;
		}
		
		private int id;
	
		@Setter
		private List<CubesSet> cubesSets;
		
	}
	
	@Getter
	protected class CubesSet {
		
		public CubesSet(int redCubes, int greenCubes, int blueCubes) {
			this.redCubes = redCubes;
			this.greenCubes = greenCubes;
			this.blueCubes = blueCubes;
		}
		
		private int redCubes;
		
		private int greenCubes;
		
		private int blueCubes;	
	}	
}
