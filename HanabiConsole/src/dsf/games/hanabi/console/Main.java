package dsf.games.hanabi.console;

import dsf.games.hanabi.GameManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// First pull in the AI to use
		String aiClassName = args[0];
		String aiClassLocation = args[1];
		
		// Num players
		int numPlayers = Integer.parseInt(args[2]);
		int numTips = Integer.parseInt(args[3]);

		// Run the game
		GameManager game = new GameManager(aiClassName, aiClassLocation, numPlayers, numTips);
		game.runGame();
	}
}
