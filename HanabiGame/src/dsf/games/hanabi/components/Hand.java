package dsf.games.hanabi.components;

import java.util.ArrayList;
import java.util.List;

import dsf.games.hanabi.Player;

public class Hand {
	
	List<Card> _cards = new ArrayList<>();
	
	public Hand(){
	}
	
	public void addCard(Card card){
		_cards.add(card);
	}
	
	public List<Card> getCards() {
		return _cards;
	}
	
	public Card removeCard(int index){
		return _cards.remove((int)index);
	}
}
