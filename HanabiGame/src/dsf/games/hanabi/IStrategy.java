package dsf.games.hanabi;

import java.util.ArrayList;
import java.util.List;

import dsf.games.hanabi.action.Move;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.GameBoard;

/***
 * The only file that players need to fill out. This class will be loaded into the Console application and run as the logic for the bot.
 * @author Devon
 *
 */
public interface IStrategy {
	/***
	 * The name of the bot - something funny is strongly recommended
	 * @return
	 */
	public String getBotName();
	/***
	 * A list of phrases/taunts/whatever you want that will be displayed throughout the game to keep things interesting
	 * @return
	 */
	public List<String> getPhraseList();
	/***
	 * Players get this call once at the beginning of the game so they know the conditions of play
	 * @param you The player you are
	 * @param numPlayers The number of players in this game
	 * @param cardsPerPlayer The number of cards each player has
	 * @param totalTips The number of tips at the start of the game
	 */
	public void initialGameConditions(Player you, int numPlayers, int cardsPerPlayer, int totalTips);
	/***
	 * This is called each time it is a player's turn. This method passes the full game state to help the player make their move
	 * @param gameboard The current gameboard - this is useful to see which cards are can be successfully played
	 * @param players - All players except for you. Use this to find information about other player's hands.
	 * @param discardPile - The full discard pile
	 * @param cardsRemaining - How many cards are remaining in the deck
	 * @param stormsRemaining - The number of storms remaining until a loss
	 * @param tipsRemaining - The number of tips remaining to use
	 * @return The fully filled out move that you plan to make.
	 */
	public Move makeMove(GameBoard gameboard, ArrayList<Player> players, ArrayList<Card> discardPile, int cardsRemaining, int stormsRemaining, int tipsRemaining);
	
	/***
	 * Sent every time a move is made. Use this information to store what has been happening during the game so that you can make a more informed move on your turn
	 * @param actingPlayer The player that made the move
	 * @param move The move that was made 
	 * @param gameboard The current gameboard - this is useful to see which cards are can be successfully played
	 * @param discardPile - The full discard pile
	 * @param cardsRemaining - How many cards are remaining in the deck
	 * @param stormsRemaining - The number of storms remaining until a loss
	 * @param tipsRemaining - The number of tips remaining to use
	 */
	public void moveMade(Player actingPlayer, Move move, GameBoard gameboard, ArrayList<Card> discardPile, int cardsRemaining, int stormsRemaining, int tipsRemaining);
}
