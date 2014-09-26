package dsf.games.hanabi;

import dsf.games.hanabi.action.Hint;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.Deck;
import dsf.games.hanabi.components.Hand;
import dsf.games.hanabi.components.HiddenHint;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

public class Player {
	private static final Logger log = LogManager.getLogger(Player.class.getName());
	private IStrategy _strategy;
	private Hand _hand;
	private Deck _deck;
	
	
	public Player(Deck deck, IStrategy strategy, int initialHandSize){
		_strategy = strategy;
		_deck = deck;
		
		initialize(initialHandSize);
	}
	
	public IStrategy getStrategy(){
		return _strategy;
	}
	
	public Hand getHand() {
		return _hand;
	}
	
	public void receiveHint(Player fromPlayer, Hint hint) {		
		HiddenHint hh = new HiddenHint(hint, _hand);
		_strategy.receiveHint(fromPlayer, hh);
	}
	
	public void drawCard() {
		if(_deck.getRemainingCardCount() != 0)
			_hand.addCard(_deck.takeCard());
		
		if(_hand.getCards().size() != 4){
			log.debug("got here");
			printHand(this);
		}
	}
	
	private void initialize(int initialHandSize){
		_hand = new Hand();
		
		for(int i = 0; i < initialHandSize; i++){
			_hand.addCard(_deck.takeCard());
		}
	}
	
	public static void printHand(Player player){
		String handcards = "";
		for(Card c : player.getHand().getCards()){
			handcards += "[" + c.getColor() + " " + c.getValue() +"]";
		}
		
		log.debug("Hand: " + handcards);
	}
}