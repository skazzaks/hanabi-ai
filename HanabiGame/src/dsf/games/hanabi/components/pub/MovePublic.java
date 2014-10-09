package dsf.games.hanabi.components.pub;

import dsf.games.hanabi.action.Move;
import dsf.games.hanabi.action.MoveType;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.HiddenHint;

public class MovePublic {
	private Move _move = null;
	
	public MovePublic(Move move){
		_move = move;
	}
	
	public MoveType getMoveType(){
		return _move.getMoveType();
	}
	
	public HiddenHint getHiddenHint(){
		return _move.getHiddenHint();
	}
	
	public int getPlayedCard(){
		return _move.getPlayedCard();
	}
	
	public int getDiscardedCard(){
		return _move.getDiscardedCard();
	}
}
