package dsf.games.hanabi.components;

import java.util.HashMap;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

public class GameBoard {
	
	private static final Logger log = LogManager.getLogger(GameBoard.class.getName());
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
	
	private void initialize(){
		_board = new HashMap<>();
		_board.put(CardColor.Blue, 0);
		_board.put(CardColor.Green, 0);
		_board.put(CardColor.Purple, 0);
		_board.put(CardColor.Red, 0);
		_board.put(CardColor.Yellow, 0);
	}
}
