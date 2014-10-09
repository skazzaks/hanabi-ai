package dsf.games.hanabi.components.pub;

import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.CardColor;
import dsf.games.hanabi.components.GameBoard;

public class GameBoardPublic {

	private GameBoard _gameBoard = null;
	
	public GameBoardPublic(GameBoard gb){
		_gameBoard = gb;
	}
	
	public boolean isCardValid(Card card){
		return _gameBoard.isCardValid(card);
	}
		
	public boolean cardStillHasWorth(Card card) {
		return _gameBoard.cardStillHasWorth(card);
	}
	
	
	public int getCountOfNumberStillValid(int number){
		return _gameBoard.getCountOfNumberStillValid(number);
	}
	
}
