package dsf.helpers.game;

import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.GameBoard;

public class BotLogicHelpers {
	
	/***
	 * Returns whether or not the specified card can be played validly on the board at this point in time.
	 * @param gameboard The current gameboard
	 * @param card The card to be played
	 * @return Whether or not the card can be played
	 */
	public static boolean cardIsValid(GameBoard gameboard, Card card){
		return gameboard.isCardValid(card);
	}
	
	public static boolean cardStillHasValue(GameBoard gameboard, Card card) {
		return gameboard.cardStillHasWorth(card);
	}
}
