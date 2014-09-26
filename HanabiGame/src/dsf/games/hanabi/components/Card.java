package dsf.games.hanabi.components;

public class Card {
	private CardColor _color;
	private int _value;
	
	/***
	 * A simple class that manages a card in the deck.
	 * @param color The color of the card.
	 * @param value The value of the card;
	 */
	public Card(CardColor color, int value){
		_color = color;
		_value = value;
	}
	
	public CardColor getColor(){
		return _color;
	}
	
	public int getValue(){
		return _value;
	}
}
