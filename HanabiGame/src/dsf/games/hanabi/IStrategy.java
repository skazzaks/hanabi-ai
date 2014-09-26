package dsf.games.hanabi;

import java.util.ArrayList;

import dsf.games.hanabi.action.Hint;
import dsf.games.hanabi.action.Move;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.GameBoard;
import dsf.games.hanabi.components.Hand;
import dsf.games.hanabi.components.HiddenHint;

public interface IStrategy {
	public String getBotName();
	public String getRandomPhrase();
	public void initialGameConditions(int numPlayers, int totalTips);
	public void receiveHint(Player fromPlayer, HiddenHint hint);
	public Move makeMove(GameBoard gameboard, ArrayList<Player> players, ArrayList<Card> discardPile, int cardsRemaining, int stormsRemaining, int tipsRemaining);	
}
