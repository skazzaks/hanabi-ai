package dsf.games.hanabi.components;

import java.util.ArrayList;

import dsf.games.hanabi.action.Hint;
import dsf.games.hanabi.action.HintType;

public class HiddenHint {
	private CardColor _color = null;
	private Integer _value = null;
	private ArrayList<Boolean> _hand = null;
	private HintType _hintType = null;
	
	public HiddenHint(Hint hint, Hand hand){
		_hand = new ArrayList<>();
		
		convertHintToHiddenHint(hint, hand);
	}
	
	public ArrayList<Boolean> getHiddenHand(){
		return _hand;
	}
	
	public CardColor getColor(){
		return _color;
	}
	
	public Integer getValue(){
		return _value;
	}
	
	public HintType getHintType(){
		return _hintType;
	}
	
	private void convertHintToHiddenHint(Hint hint, Hand hand){
		_hintType = hint.getHintType();
		
		if(hint.getHintType() == _hintType.Color){
			_color = hint.getCardColor();
			for(int i = 0; i < hand.getCards().size(); i++)
				_hand.add(hand.getCards().get(i).getColor() == hint.getCardColor());
		}
		
		if(hint.getHintType() == _hintType.Value){
			_value = hint.getCardValue();
			for(int i = 0; i < hand.getCards().size(); i++)
				_hand.add(hand.getCards().get(i).getValue() == hint.getCardValue());
		}
	}
}
