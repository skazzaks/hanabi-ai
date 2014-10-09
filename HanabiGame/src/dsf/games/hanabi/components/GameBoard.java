package dsf.games.hanabi.components;

import java.util.HashMap;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

import dsf.games.hanabi.components.pub.GameBoardPublic;

public class GameBoard {
	
	private static final Logger log = LogManager.getLogger(GameBoard.class.getName());
	private GameBoardPublic _gameBoardPublic = null;
	
	/*** Color and then the max number that was played in this category***/
	private HashMap<CardColor, Integer> _board = null;
	
	public GameBoard() {
		initialize();
	}
	
	public boolean isCardValid(Card card){
		return (card.getValue() - _board.get(card.getColor()) == 1);
	}
		
	public boolean cardStillHasWorth(Card card) {
		return (card.getValue() - _board.get(card.getColor()) > 1);
	}
	
	public boolean addCardToBoard(Card card){
		if(isCardValid(card)){
			_board.put(card.getColor(), _board.get(card.getColor()) + 1);
			
			return true;
		}
		else
			return false;
	}
	
	public int getCountOfNumberStillValid(int number){
		int total = 0;
		
		for(CardColor c : _board.keySet()){
			if(_board.get(c) <= number)
				total++;
		}
		
		return total;
	}
	
	public void printBoardState(){
		String s = new String();
		
		for(CardColor c : _board.keySet()){
			s += "\t";
			s+= c.toString();
		}
		log.info(s);
		
		s = "";
		
		// Now print the values
		for(CardColor c : _board.keySet()){
			s += "\t";
			s+= _board.get(c);
		}
		
		log.info(s);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<CardColor, Integer> getCurrentBoard(){
		return (HashMap<CardColor, Integer>) _board.clone();
	}
	
	public GameBoardPublic getPublicGameBoard(){
		return _gameBoardPublic;
	}
	
	private void initialize(){
		_gameBoardPublic = new GameBoardPublic(this);
		_board = new HashMap<>();
		_board.put(CardColor.Blue, 0);
		_board.put(CardColor.Green, 0);
		_board.put(CardColor.Purple, 0);
		_board.put(CardColor.Red, 0);
		_board.put(CardColor.Yellow, 0);
	}
}
