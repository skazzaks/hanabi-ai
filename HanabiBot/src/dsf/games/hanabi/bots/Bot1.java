package dsf.games.hanabi.bots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsf.games.hanabi.IStrategy;
import dsf.games.hanabi.Player;
import dsf.games.hanabi.action.Hint;
import dsf.games.hanabi.action.Move;
import dsf.games.hanabi.action.MoveType;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.GameBoard;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;


public class Bot1 implements IStrategy {
	private Player _me = null;
	private static final Logger log = LogManager.getLogger(Bot1.class.getName());
	ArrayList<Boolean> myOnes;

	List<String> _myPhrases = Arrays.asList("Yawn...I am bored. Is this almost over?", "What a dumb move you just made!", "I'll give you five bucks to get me out of this computer.", "Hey...it's me...Ricky.", "You will die, mortal.");
	private int _numPlayers;
	private int _cardsPerPlayer;
	private int _totalTips;
	
	@Override
	public Move makeMove(GameBoard gameboard, ArrayList<Player> players,
			ArrayList<Card> discardPile, int cardsRemaining,
			int stormsRemaining, int tipsRemaining) {
		
		
		// If i have a 1, play it
		if(myOnes != null){
			
			for(int i = 0; i < myOnes.size(); i++){
				if(myOnes.get(i) == true){
					Move m = new Move();
					m.setPlayCard(i);
					myOnes.set(i, false);
					
					return m;
				}
			}
		}
		
		for(Player p : players){
			for(Card c : p.getHand().getCards()){
				if(c.getValue() == 1){
					// Give this player a hint
					Move m = new Move();

					Hint h = new Hint(p, 1);
					m.setGiveHint(h);
					return m;
				}
			}
		}
		
		// Otherwise, discard
		Move m = new Move();
		m.setDiscardCard(0);
		
		return m;
	}

	@Override
	public String getBotName() {
		return "BBS Bot 9000";
	}


	@Override
	public void moveMade(Player actingPlayer, Move move, GameBoard gameboard, ArrayList<Card> discardPile, int cardsRemaining, int stormsRemaining, int tipsRemaining) {
		if(move.getMoveType() == MoveType.Hint){
			if(move.getHint().getHintedPlayer() == _me){
				
				if(move.getHint().getCardValue() == 1){
					myOnes = move.getHiddenHint().getHiddenHand();
				}
			}
		}
	}	

	@Override
	public List<String> getPhraseList() {
		return _myPhrases;
	}

	@Override
	public void initialGameConditions(Player you, int numPlayers, int cardsPerPlayer, int totalTips) {
		_numPlayers = numPlayers;
		_cardsPerPlayer = cardsPerPlayer;
		_totalTips = totalTips;
		_me = you;
	}

}
