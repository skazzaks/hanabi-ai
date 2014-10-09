package dsf.games.hanabi.components;

import java.util.ArrayList;
import java.util.List;

import dsf.games.hanabi.action.Hint;
import dsf.games.hanabi.action.HintType;

public class Hand {
	
	List<Card> _cards = new ArrayList<>();
	List<CardInfo> _cardInfos = new ArrayList<>();
	
	public Hand(){
	}
	
	public void addCard(Card card){
		_cards.add(card);
		_cardInfos.add(new CardInfo());
	}
	
	public List<Card> getCards() {
		return _cards;
	}
	
	public Card removeCard(int index){
		_cardInfos.remove((int)index);
		return _cards.remove((int)index);
	}
	
	public void updateHandInfo(Hint hint){
		for(int i = 0; i < _cards.size(); i++){
			Card c = _cards.get(i);
			CardInfo ci = _cardInfos.get(i);
			if(hint.getHintType() == HintType.Color)
				if (c.getColor() == hint.getCardColor())
					ci.setColor(c.getColor());
			
			if(hint.getHintType() == HintType.Value)
				if (c.getValue() == hint.getCardValue()){
					ci.setValue(c.getValue());
			}
		}
	}
	
	public List<CardInfo> getCardHints(){
		return _cardInfos;
	}
	
	public int getCountOfNumber(int theNumber){
		int total = 0;
		
		for(Card c : _cards){
			if(c.getValue() == theNumber)
				total++;
		}
		
		return total;
	}
	
	public int getCountOfColor(CardColor color){
		int total = 0;
		
		for(Card c : _cards){
			if(c.getColor() == color)
				total++;
		}
		
		return total;
	}
}
