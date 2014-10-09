package dsf.helpers.game;

import java.util.List;

import dsf.games.hanabi.Player;
import dsf.games.hanabi.components.Card;
import dsf.games.hanabi.components.CardColor;
import dsf.games.hanabi.components.CardInfo;
import dsf.games.hanabi.components.GameBoard;
import dsf.games.hanabi.components.Hand;
import dsf.games.hanabi.components.pub.GameBoardPublic;

public class BotLogicHelpers {
	
	/***
	 * Returns whether or not the specified card can be played validly on the board at this point in time.
	 * @param gameboard The current gameboard
	 * @param card The card to be played
	 * @return Whether or not the card can be played
	 */
	public static boolean cardIsValid(GameBoardPublic gameboard, Card card){
		return gameboard.isCardValid(card);
	}
	
	
	public static boolean cardIsValid(GameBoardPublic gameboard, CardInfo ci){
		if(ci.getColor() != null && ci.getValue() != null){
			return gameboard.isCardValid(new Card(ci.getColor(), ci.getValue()));
		}
		
		return false;
	}
	
	/***
	 * Returns whether or not the card can eventually (including right now) be played successfully.
	 * @param gameboard
	 * @param card
	 * @return
	 */
	public static boolean cardStillHasValue(GameBoardPublic gameboard, Card card) {
		return gameboard.cardStillHasWorth(card);
	}
	
	public static boolean cardStillHasValue(GameBoardPublic gameboard, CardInfo ci){
		if(ci.getColor() != null && ci.getValue() != null){
			return gameboard.cardStillHasWorth(new Card(ci.getColor(), ci.getValue()));
		}
		
		return false;
	}
	
	public static int getCountOfNumber(Hand hand, int theNumber){
		return hand.getCountOfNumber(theNumber);
	}
	
	public static int getCountOfColor(Hand hand, CardColor theColor){
		return hand.getCountOfColor(theColor);
	}
	
	public static int getHowManyOfNumberStillValid(GameBoardPublic gameboard, int number){
		return gameboard.getCountOfNumberStillValid(number);
	}
	
	public static int getKnownCountOfNumber(Player player, int number){
		return getKnownCountOfNumber(player.getHand().getCardHints(), number);
	}
	
	public static int getKnownCountOfNumber(List<CardInfo> cardHints, int number){
		int total = 0;
		for(CardInfo ci : cardHints){
			if(ci.getValue() != null && ci.getValue() == number){
				total++;
			}
		}
		
		return total;
	}
	public static int getKnownCountOfColor(Player player, CardColor color){
		return getKnownCountOfColor(player.getHand().getCardHints(), color);
	}
		
	public static int getKnownCountOfColor(List<CardInfo> cardHints, CardColor color){
		int total = 0;
		for(CardInfo ci : cardHints){
			if(ci.getColor() != null && ci.getColor() == color){
				total++;
			}
		}
		return total;
	}
}
