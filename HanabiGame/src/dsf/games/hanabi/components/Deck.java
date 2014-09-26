package dsf.games.hanabi.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

import dsf.games.hanabi.GameManager;

public class Deck {
	private static final Logger log = LogManager.getLogger(Deck.class.getName());
	// Underlying structure for deck of cards
	Stack<Card> _cards = null;
	
	public Deck() {
		initialize();
	}
	
	public void initialize(){
		// Reset data structure
		_cards = new Stack<>();
		
		List<Card> tempDeck = new ArrayList<>();
		
		// Add all of our cards
		for(CardColor c : CardColor.values())
			addColorToTempDeck(tempDeck, c);	
		
		// Randomize
		Collections.shuffle(tempDeck, new Random(System.currentTimeMillis()));
		
		// copy the elements to our stack
		_cards.addAll(tempDeck);
	}
	
	public Card takeCard(){
		if(_cards.size() != 0){
			log.debug("\tCard taken! Remaining count is: " + (getRemainingCardCount() - 1));
			return _cards.pop();
			
		}
		
		return null;
	}
	
	public int getRemainingCardCount(){
		return _cards.size();
	}
	
	private void addColorToTempDeck(List<Card> tempDeck, CardColor color){
		// 3 1s, 2 2s, 2 3s 2 4s, 1 5
		addCardToTempDeck(tempDeck, color, 1, 3);
		addCardToTempDeck(tempDeck, color, 2, 2);
		addCardToTempDeck(tempDeck, color, 3, 2);
		addCardToTempDeck(tempDeck, color, 4, 2);
		addCardToTempDeck(tempDeck, color, 5, 1);
	}
	
	private void addCardToTempDeck(List<Card> tempDeck, CardColor color, int value, int numTimes){
		for(int i = 0; i < numTimes; i++)
			tempDeck.add(new Card(color, value));
	}
	
}
