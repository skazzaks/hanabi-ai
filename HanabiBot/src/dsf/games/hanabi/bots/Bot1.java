package dsf.games.hanabi.bots;

import java.util.ArrayList;

import dsf.games.hanabi.IStrategy;
import dsf.games.hanabi.Player;
import dsf.games.hanabi.action.Hint;
import dsf.games.hanabi.action.HintType;
import dsf.games.hanabi.action.Move;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.GameBoard;
import dsf.games.hanabi.components.HiddenHint;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;


public class Bot1 implements IStrategy {
	private static final Logger log = LogManager.getLogger(Bot1.class.getName());
	ArrayList<Boolean> myOnes;
	@Override
	public void initialGameConditions(int numPlayers, int numTips) {
		
	}

	@Override
	public void receiveHint(Player fromPlayer, HiddenHint hint) {
		if(hint.getHintType() == HintType.Value && hint.getValue() == 1){
			myOnes = hint.getHiddenHand();
		}
	}

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
	public String getRandomPhrase() {
		return "I'm so bored...are we almost done?";
	}

}
