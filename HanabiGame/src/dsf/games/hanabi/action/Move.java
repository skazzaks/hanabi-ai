package dsf.games.hanabi.action;

import dsf.games.hanabi.GameManager;
import dsf.games.hanabi.components.Card;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

public class Move {
	private static final Logger log = LogManager.getLogger(Move.class.getName());
	private MoveType _moveType = null;
	private Hint _hint = null;
	private Integer _discardCard = null;
	private Integer _playCard = null;
	
	public Move(){}
	
	public void setGiveHint(Hint hint){
		initialize();
		_hint = hint;
		_moveType = MoveType.Hint;
	}
	
	public void setDiscardCard(int cardToDiscard){
		initialize();
		_moveType = MoveType.Discard;
		_discardCard = cardToDiscard;
		
	}
	
	public void setPlayCard(int cardToPlay){
		initialize();
		_moveType = MoveType.Play;
		_playCard = cardToPlay;
	}
	
	public Integer getDiscardedCard(){
		return _discardCard;
	}
	
	public Integer getPlayedCard(){
		return _playCard;
	}
	
	public MoveType getMoveType(){
		return _moveType;
	}
	
	public Hint getHint(){
		return _hint;
	}
	private void initialize(){
		_moveType = null;
		_hint = null;
		_discardCard = null;
		_playCard = null;
	}
}
