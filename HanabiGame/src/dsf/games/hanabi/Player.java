package dsf.games.hanabi;

import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.CardInfo;
import dsf.games.hanabi.components.Deck;
import dsf.games.hanabi.components.Hand;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

public class Player {
	private static final Logger log = LogManager.getLogger(Player.class.getName());
	private IStrategy _strategy;
	private Hand _hand;
	private Deck _deck;
	private int playerNumber;
	
	public Player(Deck deck, IStrategy strategy, int initialHandSize, int playerNumber){
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
	
	public int getPlayerNumber(){
		return playerNumber;
	}
	
	public void drawCard() {
		if(_deck.getRemainingCardCount() != 0)
			_hand.addCard(_deck.takeCard());
		
		if(_hand.getCards().size() != 4){
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
	
	public static void printHandHints(Player player){
		String handcards = "";
		for(CardInfo c : player.getHand().getCardHints()){
			handcards += "[";
			if(c.getColor() != null){
				handcards += c.getColor();
			}
			handcards += ",";
			if(c.getValue() != null){
				handcards += c.getValue().toString();
			}
			handcards += "]";
		}
		
		log.debug("Hand Hints: " + handcards);
	}
}