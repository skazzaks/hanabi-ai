package dsf.games.hanabi.action;

import dsf.games.hanabi.Player;
import dsf.games.hanabi.components.CardColor;

public class Hint {
	private int _playerToGiveHint;
	private HintType _hintType;
	private CardColor _cardColor = null;
	private Integer _cardValue;
	
	public Hint(int playerToGiveHint, CardColor cardColor)
	{
		_playerToGiveHint = playerToGiveHint;
		_cardColor = cardColor;
		_hintType = HintType.Color;
	}
	
	public Hint(int playerToGiveHint, int value)
	{
		_playerToGiveHint = playerToGiveHint;
		_cardValue = value;
		_hintType = HintType.Value;
	}
	
	public int getHintedPlayer() {
		return _playerToGiveHint;
	}
	
	public HintType getHintType() {
		return _hintType;
	}

	public CardColor getCardColor() {
		return _cardColor;
	}

	public Integer getCardValue() {
		return _cardValue;
	}
}
