package dsf.games.hanabi.components.pub;

import java.util.List;

import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.CardInfo;
import dsf.games.hanabi.components.Hand;

public class HandPublic {

	private Hand _hand = null;
	public HandPublic(Hand hand){
		_hand = hand;
	}
	
	public List<Card> getCards(){
		return _hand.getCards();
	}
	
	public List<CardInfo> getCardHints(){
		return _hand.getCardHints();
	}
	
	
}
