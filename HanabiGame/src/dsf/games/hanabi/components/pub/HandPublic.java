package dsf.games.hanabi.components.pub;

import java.util.List;

import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.CardInfo;
import dsf.games.hanabi.components.Hand;

public class HandPublic {

	private Hand _hand = null;
	private int _playerIndex = 0;
	
	public HandPublic(Hand hand, int playerIndex){
		_hand = hand;
		_playerIndex = playerIndex;
	}
	
	public List<Card> getCards(){
		return _hand.getCards();
	}
	
	public List<CardInfo> getCardHints(){
		return _hand.getCardHints();
	}
	
	public int getPlayerIndex(){
		return _playerIndex;
	}
}
