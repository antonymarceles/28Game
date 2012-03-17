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
		 int roundStartPlayer = 2;
		 int startPlayer;
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
		 
	     System.out.println("");
		 // Call Winning Total & Fold Trump Card
		 Trump playerTrumpCards = new Trump(allPlayerHands.get(roundStartPlayer).getHand());
		 int trumpValue = playerTrumpCards.setTrumpCall();
		 System.out.println("Calling total --> "+trumpValue);
		 Card trumpCard = playerTrumpCards.setTrumpCard();
		 System.out.println("Trump Card --> "+trumpCard.getFace()+" of "+trumpCard.getSuit());
		 
		 	
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
			
		 System.out.println("");

		 //Play -- Note: Player 0 & Player 2 are teams, Player 1 & Player 3 are teams
		 int teamAScores = 0;
		 int teamBScores = 0;
		 startPlayer = roundStartPlayer;
		 
		 for (int round = 0; round < 8; round++) {

			 //cardsOnTable = null;
			 System.out.println("");
			 System.out.println("Round - "+round);
			 
			 List<Card> cardsOnTable = new ArrayList<Card>();			//List of individual Cards Played
			
			 //cardsOnTable.add(demoCard); 								//for test - should remove
			 //startPlayer = 2;
			 for (int i=0; i<4; i++) {
				 
				 if (startPlayer>3) {startPlayer = startPlayer - 4;} 
				 
				 int incrementor = startPlayer+i;
				 
				 if (incrementor>3) {incrementor = incrementor - 4;} 
	
				 Play gamePlay = new Play (cardsOnTable, allPlayerHands.get(incrementor).getHand(), startPlayer, incrementor);
				 Card playedCard = gamePlay.throwCard(); 				//Player throws Card
				 System.out.println(playedCard.getFace()+" of "+playedCard.getSuit());
				 
				 cardsOnTable.add(playedCard); 							//Add Card to Table
				 
				 List<Card> restructuredListOfCards = allPlayerHands.get(incrementor).removeCard(playedCard);
				 Hand restructuredHand = new Hand(restructuredListOfCards);
				 allPlayerHands.put(incrementor, restructuredHand);		//Remove Card from Players Hand

			 }

			 //Validate Winner of round
			 Table tableAnalyst = new Table (cardsOnTable);			
			 int pointsInRound = tableAnalyst.findTablePoints();		
			 int winningPlayer = tableAnalyst.findWinningPlayer(startPlayer);		//Team A (0&2) or Team B (0&1)			 
			 System.out.println("Winning Player --> "+winningPlayer);
		     System.out.println("Points in Round --> "+pointsInRound);
		     System.out.println("");

		     if ((winningPlayer == 0) || (winningPlayer == 2)) {
		    	 teamAScores = teamAScores + pointsInRound;
		     } else if ((winningPlayer == 1) || (winningPlayer == 3)) {
		    	 teamBScores = teamBScores + pointsInRound;
		     }
		     
			 //Assign New Start Player (have to modify Play.java to ensure player can start game)			 
			 startPlayer = winningPlayer;
		 }
		 
		 //Declare winning team
		 if ((roundStartPlayer == 0)||(roundStartPlayer == 2)) {
			 if (teamAScores >= trumpValue) {
			     System.out.println("Team A called "+trumpValue+" and won "+teamAScores+", and hence won the game");
			 } else {
			     System.out.println("Team A called "+trumpValue+" and won only "+teamAScores+", and hence lost the game");
			 }
		 } else if ((roundStartPlayer == 1)||(roundStartPlayer == 3)) {
			 if (teamBScores == trumpValue) {
			     System.out.println("Team B called "+trumpValue+" and won "+teamBScores+", and hence won the game");
			 } else {
			     System.out.println("Team B called "+trumpValue+" and won only "+teamBScores+", and hence lost the game");
			 } 
		 }
		 
	} // end main

} // end class