package dsf.games.hanabi.action;

import dsf.games.hanabi.Player;
import dsf.games.hanabi.components.CardColor;

public class Hint {
	private Player _playerToGiveHint;
	private HintType _hintType;
	private CardColor _cardColor = null;
	private Integer _cardValue;
	
	public Hint(Player playerToGiveHint, CardColor cardColor)
	{
		_playerToGiveHint = playerToGiveHint;
		_cardColor = cardColor;
		_hintType = HintType.Color;
	}
	
	public Hint(Player playerToGiveHint, int value)
	{
		_playerToGiveHint = playerToGiveHint;
		_cardValue = value;
		_hintType = HintType.Value;
	}
	
	public Player getHintedPlayer() {
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
