package dsf.games.hanabi.components;

public class CardInfo {
	private Integer _value = null;
	private CardColor _color = null;
	
	public CardInfo() {
	}
	
	public Integer getValue() {
		return _value;
	}
	
	public void setValue(int value) {
		_value = value;
	}
	
	public CardColor getColor()
	{
		return _color;
	}
	
	public void setColor(CardColor color) {
		_color = color;
	}
}
