package dsf.games.hanabi;

import java.util.ArrayList;
import java.util.Random;

import dsf.games.hanabi.action.HintType;
import dsf.games.hanabi.action.Move;
import dsf.games.hanabi.action.MoveType;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.Deck;
import dsf.games.hanabi.components.GameBoard;
import dsf.helpers.MyClassLoader;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

public class GameManager {

	private static final Logger log = LogManager.getLogger(GameManager.class.getName());
	private String _aiClassName = null;
	private String _aiClassLocation = null;
	private int _numPlayers = 0;
	private int _numTips = 0;
	private int _numAvailTips = 0;
	private int _remainingStorms = 3;
	private Player _finalPlayer = null;
	private GameBoard _board = null;
	private Deck _deck = null;
	private ArrayList<Player> _players = new ArrayList<>();
	private ArrayList<Card> _discardPile = new ArrayList<Card>();
	
	public GameManager(String aiClassName, String aiClassLocation, int numPlayers, int numTips)
	{
		log.info("This is an info message."); 
		_aiClassName = aiClassName;
		_aiClassLocation = aiClassLocation;
		_numPlayers = numPlayers;
		_numTips = numTips;
		_numAvailTips = _numTips;
		
		initialize(aiClassName, aiClassLocation, numPlayers);
	}

	public void runGame()
	{
		log.info("Welcome to Hanabi! A new game is about to start.");
		log.info("------------------------------------------------");
		
		// Assign Starting Player
		Player currentPlayer = determineStartingPlayer();
		
		while(true)
		{
			// Get the players move
			log.info("Player " + (_players.indexOf(currentPlayer) + 1) + " is up.");
			Move move = null;
			// TODO DSF - need to time the user out if their logic is too slow
			Player.printHand(currentPlayer);
			try{
				move = getPlayerMove(currentPlayer);
			}
			catch(Exception e){
				move = null;
			}
			
			// Now, handle the move we got back
			// TODO DSF handle move
			handleMove(move, currentPlayer);			
			
			// Check for the end of game condition
			if(isItEndOfGame(currentPlayer))
				break;
			
			// Update to the next player
			currentPlayer = getNextPlayer(currentPlayer);	
		}
		
		log.info("Game over!");
		// Print score
	}
	
	private Move getPlayerMove(Player currentPlayer){
		ArrayList<Player> players = new ArrayList<Player>();
		
		Player nextPlayer = getNextPlayer(currentPlayer);
		for(int i = 0; i < (_players.size() - 1); i ++){
			players.add(nextPlayer);
			nextPlayer = getNextPlayer(nextPlayer);
		}
		return currentPlayer.getStrategy().makeMove(_board, players, _discardPile, _deck.getRemainingCardCount(), _remainingStorms, _numAvailTips);
	}
	
	
	private Player getNextPlayer(Player currentPlayer) {
		int i = _players.indexOf(currentPlayer);
		if(i < (_players.size() - 1))
			i++;
		else
			i = 0;
		
		return _players.get(i);
		
	}

	private void handleMove(Move move, Player currentPlayer){
		boolean moveWasValid = false;
		
		if(move == null){
			moveWasValid = false;
		}
		else if(move.getMoveType() == MoveType.Hint){
			moveWasValid = handleHintMove(move, currentPlayer);
		}
		else if(move.getMoveType() == MoveType.Discard){
			moveWasValid = handleDiscardMove(move, currentPlayer);
		}
		else if(move.getMoveType() == MoveType.Play){
			moveWasValid = handlePlayMove(move, currentPlayer);
		}
		
		// If they didn't  do a valid move, we should just do a default crappy move
		if(!moveWasValid){
			log.info("\tInvalid move!");
			performDefaultMove(currentPlayer);
		}
	}
	
	private void performDefaultMove(Player currentPlayer) {
		log.info("\tPerforming default move...");
		// Build up a Discard Move
		Move m = new Move();
		m.setDiscardCard(0);
		
		handleDiscardMove(m, currentPlayer);
	}
	
	private boolean handlePlayMove(Move move, Player currentPlayer) {
		// Bail out if they build up a bad move
		if(move.getPlayedCard() == null)
			return false;
		
		// The player plays a card
		Card c =currentPlayer.getHand().getCards().get(move.getPlayedCard());
		
		log.info("\tPlayed: " + c.getColor() + " " + c.getValue());
		Player.printHand(currentPlayer);
		log.debug(move.getPlayedCard());
		currentPlayer.getHand().removeCard(move.getPlayedCard());
		Player.printHand(currentPlayer);
		
		
		// If the play was valid...
		if(_board.isCardValid(c)){
			log.info("\t...and it fit!");

			//...add it to the board
			_board.addCardToBoard(c);
			
			log.info("\tThe new board state is:");
			_board.printBoardState();
		} 
		// Otherwise, they get a lightning!
		else {
			_remainingStorms--;
			log.info("\t...and it didn't work! " + _remainingStorms + " storm" + (_remainingStorms == 1 ? "" : "s") + " remaining.");
		}
		
		// Then draws a new one
		currentPlayer.drawCard();
		
		return true;
	}

	private boolean handleDiscardMove(Move move, Player currentPlayer) {
		// Bail out if they built up a bad move
		if(move.getDiscardedCard() == null)
			return false;
		
		// The player discards a card
		Card c = currentPlayer.getHand().getCards().get(move.getDiscardedCard());
		
		log.info("\tDiscarded: " + c.getColor() + " " + c.getValue());
		currentPlayer.getHand().removeCard(move.getDiscardedCard());
		
		// Add to the discard pile
		_discardPile.add(c);
		// Then draws a new one
		currentPlayer.drawCard();
		// The team gets a tip back, unless they have the max
		increaseAvailableTipCount();
		
		return true;
	}

	private boolean handleHintMove(Move move, Player currentPlayer) {
		// validate that player did not send a hint to themself
		if(currentPlayer == move.getHint().getHintedPlayer())
			return false;
		
		if(move.getHint().getHintType() == HintType.Color && move.getHint().getCardColor() == null)
			return false;
		else if(move.getHint().getHintType() == HintType.Value && move.getHint().getCardValue() == null)
			return false;
		
		String hintValue = "";
		if(move.getHint().getHintType() == HintType.Color)
			hintValue = move.getHint().getCardColor().toString();
		else if(move.getHint().getHintType() == HintType.Value)
			hintValue = move.getHint().getCardValue().toString();
		
		log.info("\tGave hint to Player " + (_players.indexOf(move.getHint().getHintedPlayer()) + 1) + ": " +  hintValue + ".");
		
		// Otherwise, the tip is valid, so give it to the player
		move.getHint().getHintedPlayer().receiveHint(currentPlayer, move.getHint());
		
		// Discrease the tip count
		decreaseAvailableTipCount();
		
		return true;
	}
	
	private void increaseAvailableTipCount() {
		if(_numAvailTips != _numTips)
			_numAvailTips++;
	}
	
	private void decreaseAvailableTipCount() {
		if(_numAvailTips != 0)
			_numAvailTips--;
	}


	private boolean isItEndOfGame(Player currentPlayer){
		// End of the loop logic
		if(_remainingStorms == 0)
			return true;
		
		if(_deck.getRemainingCardCount() == 0){
			if(_finalPlayer == null)
				_finalPlayer = currentPlayer;
			else if (_finalPlayer == currentPlayer)
				return true;
			else
				return false;
		}
		
		return false;
	}
	
	private Player determineStartingPlayer(){
		Random r = new Random(System.currentTimeMillis());
		return _players.get(r.nextInt(_numPlayers));
		
	}
	
	private void initialize(String aiClassName, String aiClassLocation, int numPlayers){
	
		_deck = new Deck();
		// 2-3 players: 5 cards, 4-5 players: 4 cards
		int startingCards = (numPlayers == 2 || numPlayers == 3) ? 5 : 4; 
		
		// TODO Get the AI Class
		IStrategy theStrat = null;
		try
		{
			theStrat = loadInStrategy();
		}
		catch(Exception e){
			System.out.println("Could not load in strategy file!");
			e.printStackTrace();
		}
		
		// Setup the Gameboard
		_board = new GameBoard();
		
		// Setup the players
		for(int i = 0; i < numPlayers; i++){
			theStrat.initialGameConditions(_numPlayers, _numTips);
			_players.add(new Player(_deck, theStrat, startingCards));	
		}
	}

	private IStrategy loadInStrategy() throws ClassNotFoundException,
    IllegalAccessException,
    InstantiationException {
		ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
	    MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
	    Class myObjectClass = classLoader.loadClass(_aiClassName, "file:" + _aiClassLocation);
		return (IStrategy) myObjectClass.newInstance();
	}
}
