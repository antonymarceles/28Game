import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		 DeckOfCards deck = new DeckOfCards();
		 List<List> playerHands = new ArrayList<List>();
		 Hand indPlayerHand = null;												//players hand 
		 Map<Integer,Hand> allPlayerHands = new HashMap<Integer,Hand> ();		//List of all players Hand
		 Table indRoundCards = null;											//cards on table in each round
		 List<Table> totalTableRound = new ArrayList<Table>();					//List of all rounds of Cards
		 int numberPlayers = 4;	
		 int numberShuffles = 4;
		 int startPlayer = 2;
		 Card demoCard = new Card("Ace", "Spades");								//for test - should remove
		 
		 // shuffling deck 4 times
	     for (int i = 0; i < numberShuffles; i++) {
			 deck.shuffle();
	     } 
	     
		 // Deal first round of Cards for 4 players	
		 for (int playercount = 0; playercount <numberPlayers; playercount++) {
			 List<Card> handCards = new ArrayList<Card>();						//List of individual Cards Dealt

					 for (int i = 0; i < 4; i++) {
						  handCards.add(deck.dealCard());
			         } // end for loop = deal one hand of poker = 5 cards
			
		     playerHands.add(handCards);
		     System.out.println("");
		     System.out.println("*****Player "+playercount+" Hand in 1st round*****");
			 indPlayerHand = new Hand(playerHands.get(playercount));
			 indPlayerHand.show();
			 allPlayerHands.put(playercount, indPlayerHand);
			 //allPlayerHands.add(indPlayerHand);

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
			 allPlayerHands.remove(playercount);
			 allPlayerHands.put(playercount, indPlayerHand);
		 }
		 

		 //Play -- Note: Player 0 & Player 2 are teams, Player 1 & Player 3 are teams
		 List<List> teamScores = new ArrayList<List>();  
		 List teamAScores = new ArrayList ();
		 List teamBScores = new ArrayList ();
		 
		 for (int round = 0; round < 8; round++) {

			 //cardsOnTable = null;
			 System.out.println("");
			 System.out.println("Round - "+round);
			 
			 List<Card> cardsOnTable = new ArrayList<Card>();			//List of individual Cards Played
			 //cardsOnTable.add(demoCard); 								//for test - should remove

			 //startPlayer = 2;
			 for (int i=0; i<4; i++) {
				 
				 if (startPlayer>3) {
					 startPlayer = startPlayer - 4;
				 } 
				 
				 int incrementor = startPlayer+i;
				 
				 if (incrementor>3) {
					 incrementor = incrementor - 4;
				 } 
	
				 Play gamePlay = new Play (cardsOnTable, allPlayerHands.get(incrementor).getHand(), startPlayer, incrementor);
				 Card playedCard = gamePlay.throwCard(); 				//Player throws Card
				 
				 System.out.println(playedCard.getFace()+" of "+playedCard.getSuit());
				 
				 cardsOnTable.add(playedCard); 							//Add Card to Table
				 
				 List<Card> restructuredListOfCards = allPlayerHands.get(incrementor).removeCard(playedCard);
				 Hand restructuredHand = new Hand(restructuredListOfCards);
				 allPlayerHands.put(incrementor, restructuredHand);		//Remove Card from Players Hand

			 }

			 //cardsOnTable.remove(0);
			 //Validate Winner
			 Table tableAnalyst = new Table (cardsOnTable);			
			 int pointsInRound = tableAnalyst.findTablePoints();		
			 int winningPlayer = tableAnalyst.findWinningPlayer(startPlayer);		//Team A (0&2) or Team B (0&1)
			 
		     System.out.println("Winning Player --> "+winningPlayer);

			 
			 //Assign New Start Player (have to modify Play.java to ensure player can start game)			 
			 startPlayer = winningPlayer;
		 }
		 
	} // end main

} // end class