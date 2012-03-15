import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Play {

	List<Card> cardsOnTable;
	List<Card> cardsOnHand;
	int startPlayer;
	int currentPlayer;
    
	public Play(List<Card> cardsOnTable, List<Card> cardsOnHand, int startPlayer, int currentPlayer) // constructor
    {
		this.cardsOnTable = cardsOnTable;
		this.cardsOnHand = cardsOnHand;
		this.startPlayer = startPlayer;
		this.currentPlayer = currentPlayer;

    } // end constructor
    
	public Card throwCard() {
		
		 String[] faces = new String [8];  
		 String[] suits = new String [8];  
		 int[] points = new int [8];
		 int[] values = new int [8];
		 
		 String openingSuit;
		 List<Integer> allPlayerCards = new ArrayList<Integer> ();
		 List<Integer> playableCards = new ArrayList<Integer> ();
		 List<Integer> trumpCards = new ArrayList<Integer> ();
		 Map<Integer,Integer> cardsPlayedPlayers = new HashMap<Integer,Integer> (); 
		 int playedCardValue;
		 boolean noTrumpCard = false;
		 
		// System.out.println(Trump.trumpCard);
		
			if (this.cardsOnTable == null) { 

			} else {
				//find Opening suit
				openingSuit = cardsOnTable.get(0).getSuit();
				
				//find the cards that are playable of the same suit
				for (int i = 0; i < this.cardsOnHand.size(); i++) {
					suits[i] = this.cardsOnHand.get(i).getSuit();	
					allPlayerCards.add(this.cardsOnHand.get(i).getValue());
					if (suits[i] == openingSuit) {
						playableCards.add(this.cardsOnHand.get(i).getValue());
					}
					if (suits[i] == Trump.trumpSuit) {
						trumpCards.add(this.cardsOnHand.get(i).getValue());
					}
				}
				Collections.sort(allPlayerCards);
				Collections.sort(playableCards);	
				Collections.sort(trumpCards);
				
				//find the Card played by each player
				for (int i = 0; i < this.cardsOnTable.size(); i++) {
					values[i] = this.cardsOnTable.get(i).getValue();
					cardsPlayedPlayers.put((this.startPlayer + 0), values[i]);
					System.out.println(cardsPlayedPlayers.get((this.startPlayer + 0)));
				}
				
				//find if there was a cut
				int playerWhoCut = 99;
				int cutCardValue = 0;
				for (int i = 0; i < this.cardsOnTable.size(); i++) {
					suits[i] = this.cardsOnTable.get(i).getSuit();	
					if (suits[i] != openingSuit) {
						if (suits[i] == Trump.trumpSuit) {
							playerWhoCut = i;
							cutCardValue = this.cardsOnTable.get(i).getValue();	
						}
					}
				}
				
				//Actual play logic (incomplete)
				if ((this.currentPlayer == 0) || (this.currentPlayer == 2)) {
					if ((this.startPlayer == 0) || (this.startPlayer == 2)) {
						//Own team
						if(playableCards.isEmpty()){
							//if previous cut... check if trump present... if present... over cut...
							//if not present... lowest non-relevant card.
							if (playerWhoCut != 99) {
								//need to check who is playing next (pending)
								playedCardValue = allPlayerCards.get(allPlayerCards.size()-1);
							} else {
								if ((playerWhoCut == 0) || (playerWhoCut == 2)) {
									playedCardValue = allPlayerCards.get(allPlayerCards.size()-1);
								} else if  ((playerWhoCut == 1) || (playerWhoCut == 3)) {
									if (trumpCards.isEmpty()) {
										playedCardValue = allPlayerCards.get(0);
									} else {
										for (int i = 0; i < trumpCards.size(); i++) {
											if (trumpCards.get(trumpCards.size()-1) > cutCardValue) {
												playedCardValue = trumpCards.get(trumpCards.size()-1);
												break;
											} else {
												noTrumpCard = true;
											}
										}
										if (noTrumpCard = true) {
											playedCardValue = allPlayerCards.get(0);
										}	
									} 
								}
							}
							
						} else {
							//GetLargest & Play Cos same team
							//need to check if there was a cut before...  (Pending)
							playedCardValue = playableCards.get(playableCards.size()-1);
						} // End Own Team - 0/2
						
					} else if  ((this.startPlayer == 1) || (this.startPlayer == 3)) {
						//Opposing team
						if (playableCards.isEmpty()){
							//if previous cut by own team... put highest card with points
							//if round is of trumps, check who has highest card, and put points accordingy
							//if not previously cut... open tump & cut.
							
						} else {
							//Get smallest & Play Cos different team
							playedCardValue = playableCards.get(0);
						}
					} // End Opposing Team 0/2 against 1/3
					
				} else if ((this.currentPlayer == 1) || (this.currentPlayer == 3)) {
					if ((this.startPlayer == 0) || (this.startPlayer == 2)) {
						//Opposing team
						if(playableCards != null){
							//Get smallest & Play Cos different team
						} else if (playableCards == null){
							//if previous cut by own team... put highest card with points
							//if round is of trumps, check who has highest card, and put points accordingy
							//if not previously cut... open tump & cut.
						} // End Opposing Team 1/3 against 0/2
						
					} else if  ((this.startPlayer == 1) || (this.startPlayer == 3)) {
						if(playableCards.isEmpty()){
							//if previous cut... check if trump present... if present... over cut...
							//if not present... lowest non-relevant card.
							if (playerWhoCut != 99) {
								playedCardValue = playableCards.get(playableCards.size()-1);
							} else {
								if ((playerWhoCut == 0) || (playerWhoCut == 2)) {
									playedCardValue = allPlayerCards.get(playableCards.size()-1);
								} else if  ((playerWhoCut == 1) || (playerWhoCut == 3)) {
									if (trumpCards.isEmpty()) {
										playedCardValue = allPlayerCards.get(0);
									} else {
										for (int i = 0; i < trumpCards.size(); i++) {
											if (trumpCards.get(playableCards.size()-1) > cutCardValue) {
												playedCardValue = trumpCards.get(playableCards.size()-1);
												break;
											} else {
												noTrumpCard = true;
											}
										}
										if (noTrumpCard = true) {
											playedCardValue = allPlayerCards.get(0);
										}	
									} 
								}
							} // End Own Team - 1/3
							
						} else {
							//GetLargest & Play Cos same team
							playedCardValue = playableCards.get(playableCards.size()-1);
						}
					}
				}
				
			}
			
		Card thrownCard = new Card("Ten","Spades");
		return thrownCard;
	}
	
}
