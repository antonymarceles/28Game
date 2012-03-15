import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		 DeckOfCards deck = new DeckOfCards();
		 List<List> playerHands = new ArrayList<List>();
		 Hand indPlayerHand = null;									//players hand 
		 List<Hand> allPlayerHands = new ArrayList<Hand>(); 		//List of all players Hand
		 Table indRoundCards = null;								//cards on table in each round
		 List<Table> totalTableRound = new ArrayList<Table>();		//List of all rounds of Cards
		 int numberPlayers = 4;
		 int numberShuffles = 4;
		 int startPlayer = 2;
		 Card demoCard = new Card("Ace", "Spades");					//for test - should remove
		 
		 // shuffling deck 4 times
	     for (int i = 0; i < numberShuffles; i++) {
			 deck.shuffle();
	     } 
	     
		 // Deal first round of Cards for 4 players	
		 for (int playercount = 0; playercount <numberPlayers; playercount++) {
			 List<Card> handCards = new ArrayList<Card>();								//List of individual Cards Dealt

					 for (int i = 0; i < 4; i++) {
						  handCards.add(deck.dealCard());
			         } // end for loop = deal one hand of poker = 5 cards
			
		     playerHands.add(handCards);
		     System.out.println("");
		     System.out.println("*****Player "+playercount+" Hand in 1st round*****");
			 indPlayerHand = new Hand(playerHands.get(playercount));
			 indPlayerHand.show();
			 allPlayerHands.add(indPlayerHand);

		 }
		 
		 // Call Winning Total & Fold Trump Card
		 Trump playerTrumpCards = new Trump(allPlayerHands.get(startPlayer).getHand());
		 int trumpValue = playerTrumpCards.setTrumpCall();
		 System.out.println(trumpValue);
		 Card trumpCard = playerTrumpCards.setTrumpCard();
		 System.out.println(trumpCard.getFace()+" of "+trumpCard.getSuit());
		 

		 // Deal second round of Cards for 4 players	
		 for (int playercount = 0; playercount <numberPlayers; playercount++) {

					 for (int i = 0; i < 4; i++) {
						  playerHands.get(playercount).add(deck.dealCard());
			         } // end for loop = deal one hand of poker = 5 cards

			System.out.println("");
		    System.out.println("*****Player "+playercount+" Hand after all cards dealt*****");
		     indPlayerHand = new Hand(playerHands.get(playercount));
			 indPlayerHand.show();
			 allPlayerHands.add(indPlayerHand);
		 }
		 

		 //Play -- Note: Player 0 & Player 2 are teams, Player 1 & Player 3 are teams
		 List<List> teamScores = new ArrayList<List>();  
		 List teamAScores = new ArrayList ();
		 List teamBScores = new ArrayList ();
		 
		 for (int round = 0; round < 8; round++) {

			 //cardsOnTable = null;
			 System.out.println("");
			 System.out.println("");
			 System.out.println("Round - "+round);
			 
			 List<Card> cardsOnTable = new ArrayList<Card>();			//List of individual Cards Played
			 cardsOnTable.add(demoCard); 								//for test - should remove


			 for (int i=startPlayer; i<startPlayer+4; i++) {
				 
				 if (i>3) {
					 startPlayer = startPlayer - 4;
				 }
				 
				 Play gamePlay = new Play (cardsOnTable, allPlayerHands.get(i).getHand(), startPlayer, i+1);
				 Card playedCard = gamePlay.throwCard(); 
				 //System.out.println(playedCard.getFace()+" of "+playedCard.getSuit());
			 }
			 
		 }
		 
	} // end main

} // end class