import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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

		 String openingSuit;
		 Map<Integer,Integer> allPlayerCards = new HashMap<Integer,Integer> (); 
		 int allHighestCardValue;
		 String allHighestCardSuit;
		 int allLowestCardValue;
		 int lowestCardPosition = 0;
		 String allLowestCardSuit;
		 //List<Double> allPlayerCards = new ArrayList<Double> ();
		 List<Integer> playableCards = new ArrayList<Integer> ();
		 List<Integer> trumpCards = new ArrayList<Integer> ();
		 int[] originTableValues = new int [8];
		 int[] allTableValues = new int [8];
		 Map<Integer,Integer> originCardsPlayedOnTable = new HashMap<Integer,Integer> (); 
		 Map<Integer,Integer> allCardsPlayedOnTable = new HashMap<Integer,Integer> (); 
		 int playedCardValue = 0;
		 String playedCardSuit = "";
		 String playedCardFace = "";
		 boolean isTrumpCard = false;
		 String currentHighestPlayer;
	 
		// System.out.println(Trump.trumpCard);
		
			if (cardsOnTable.size() == 0) { 
				
				Map<Integer,Integer> trumpOpenCards = new HashMap<Integer,Integer> ();
				Map<Integer,Integer> otherOpenCards = new HashMap<Integer,Integer> ();
				String playableCardNoText;
				int playableCardNo = 99;
				
				for (int i = 0; i<this.cardsOnHand.size(); i++) {
					if (this.cardsOnHand.get(i).getSuit() == Trump.trumpSuit) {
						trumpOpenCards.put(i, this.cardsOnHand.get(i).getValue());
					} else {
						otherOpenCards.put(i, this.cardsOnHand.get(i).getValue());
					} 
				}
				
				if (otherOpenCards.size() == 0) {
					trumpOpenCards = MapUtil.sortByValue( trumpOpenCards );			//Find Player with higest Card on table. 
					playableCardNoText = trumpOpenCards.keySet().toArray()[trumpOpenCards.size()-1].toString();
					if (playableCardNoText.equals("0")) {
						playableCardNo = 0;
					} else if (playableCardNoText.equals("1")) {
						playableCardNo = 1;
					} else if (playableCardNoText.equals("2")) {
						playableCardNo = 2;
					} else if (playableCardNoText.equals("3")) {
						playableCardNo = 3;
					} else if (playableCardNoText.equals("4")) {
						playableCardNo = 4;
					} else if (playableCardNoText.equals("5")) {
						playableCardNo = 5;
					} else if (playableCardNoText.equals("6")) {
						playableCardNo = 6;
					} else if (playableCardNoText.equals("7")) {
						playableCardNo = 7;
					}
				} else {
					otherOpenCards = MapUtil.sortByValue( otherOpenCards );			//Find Player with higest Card on table. 
					playableCardNoText = otherOpenCards.keySet().toArray()[otherOpenCards.size()-1].toString();	
					
					if (playableCardNoText.equals("0")) {
						playableCardNo = 0;
					} else if (playableCardNoText.equals("1")) {
						playableCardNo = 1;
					} else if (playableCardNoText.equals("2")) {
						playableCardNo = 2;
					} else if (playableCardNoText.equals("3")) {
						playableCardNo = 3;
					} else if (playableCardNoText.equals("4")) {
						playableCardNo = 4;
					} else if (playableCardNoText.equals("5")) {
						playableCardNo = 5;
					} else if (playableCardNoText.equals("6")) {
						playableCardNo = 6;
					} else if (playableCardNoText.equals("7")) {
						playableCardNo = 7;
					}
					
					if (this.cardsOnHand.get(playableCardNo).getValue() != 13){
						playableCardNoText = otherOpenCards.keySet().toArray()[0].toString();	
						
						if (playableCardNoText.equals("0")) {
							playableCardNo = 0;
						} else if (playableCardNoText.equals("1")) {
							playableCardNo = 1;
						} else if (playableCardNoText.equals("2")) {
							playableCardNo = 2;
						} else if (playableCardNoText.equals("3")) {
							playableCardNo = 3;
						} else if (playableCardNoText.equals("4")) {
							playableCardNo = 4;
						} else if (playableCardNoText.equals("5")) {
							playableCardNo = 5;
						} else if (playableCardNoText.equals("6")) {
							playableCardNo = 6;
						} else if (playableCardNoText.equals("7")) {
							playableCardNo = 7;
						}
					}
				}
				
				playedCardValue = this.cardsOnHand.get(playableCardNo).getValue();					 
				playedCardSuit = this.cardsOnHand.get(playableCardNo).getSuit();
				

			} else {
				//find Opening suit
				openingSuit = cardsOnTable.get(0).getSuit();
				
				//find the cards in HAND that are playable of the same suit
				int counter = 0;
				for (int i = 0; i < this.cardsOnHand.size(); i++) {
					suits[i] = this.cardsOnHand.get(i).getSuit();	
					if (suits[i].equals(openingSuit)) {
						playableCards.add(this.cardsOnHand.get(i).getValue());
						allPlayerCards.put(i, 0);
					} 
					
					if (suits[i] == Trump.trumpSuit) {
						trumpCards.add(this.cardsOnHand.get(i).getValue());
						allPlayerCards.put(i, 0);
					} 

					if (!(suits[i] == openingSuit) && !(suits[i] == Trump.trumpSuit)) {						// Adding decimals to find which Suit
						allPlayerCards.put(i, this.cardsOnHand.get(i).getValue());
					}
					
				}
				allPlayerCards = MapUtil.sortByValue( allPlayerCards );			//Find Player with higest Card on table. 
				allHighestCardValue = cardsOnHand.get(allPlayerCards.size()-1).getValue();
				allHighestCardSuit = cardsOnHand.get(allPlayerCards.size()-1).getSuit();    //(pending) - this needs to get(int of highest value in allPlayerCards) - currently wrong logic.
				
				for( int key: allPlayerCards.keySet() ){
					if (allPlayerCards.get(key) == 0) {
						
					} else {
						lowestCardPosition = key;
						break;
					}
				}
				allLowestCardValue =cardsOnHand.get(lowestCardPosition).getValue();;
				allLowestCardSuit =cardsOnHand.get(lowestCardPosition).getSuit();;
				
				Collections.sort(playableCards);	
				Collections.sort(trumpCards);
				
				//find the Card played by each player
				int counter1 = 0;
				for (int i = 0; i < this.cardsOnTable.size(); i++) {
					int playerOrderOnTable = this.startPlayer+i;
					if (playerOrderOnTable>3) {
						 playerOrderOnTable = playerOrderOnTable - 4;
					 } 
					if (cardsOnTable.get(i).getSuit() == openingSuit){
						originTableValues[counter1] = this.cardsOnTable.get(i).getValue();
						originCardsPlayedOnTable.put(playerOrderOnTable, originTableValues[counter1]);
						counter1 = counter1+1;
					}
					allTableValues[i] = this.cardsOnTable.get(i).getValue();
					allCardsPlayedOnTable.put(playerOrderOnTable, allTableValues[i]);
					//System.out.println(allCardsPlayedOnTable.get((this.startPlayer + i)));
				}
				
				//Find Highest Card player
				originCardsPlayedOnTable = MapUtil.sortByValue( originCardsPlayedOnTable );			//Find Player with higest Card on table. 
				currentHighestPlayer = originCardsPlayedOnTable.keySet().toArray()[originCardsPlayedOnTable.size()-1].toString();

				//find if there was a cut -- find player with highest trump on table (pending)
				int playerWhoCut = 99;
				int cutCardValue = 0;
				for (int i = 0; i < this.cardsOnTable.size(); i++) {
					suits[i] = this.cardsOnTable.get(i).getSuit();	
					if (suits[i] != openingSuit) {
						if (suits[i] == Trump.trumpSuit) {
							playerWhoCut = startPlayer+i;
							 if (playerWhoCut>3) {
								 playerWhoCut = playerWhoCut - 4;
							 } 
							cutCardValue = this.cardsOnTable.get(i).getValue();	
						}
					}
				}
				
				//Actual play logic (incomplete)
				if ((this.currentPlayer == 0) || (this.currentPlayer == 2)) {
					if ((this.startPlayer == 0) || (this.startPlayer == 2)) {
						//Own team
						if(playableCards.isEmpty()){													//If there are cards of same suit to play
							if (playerWhoCut == 99) {													//If NO cut so far				
								//need to check who is playing next (pending)
								//need to check who has highest card on table (pending)
								//who has highest card on table?
								//if they have current Highest player - check if my highest card > their highest card player. 
								if ((currentHighestPlayer.equals("0")) || (currentHighestPlayer.equals("2"))) {
									playedCardValue = allHighestCardValue;  						//Play Largest Card, since same team has highest card on table
									playedCardSuit = allHighestCardSuit;
								} else if ((currentHighestPlayer.equals("1")) || (currentHighestPlayer.equals("3"))) {
									playedCardValue = allLowestCardValue;							//Play Lowest Card, since opposite team has highest card on table 
									playedCardSuit = allLowestCardSuit;
								}								
							} else {
								if ((playerWhoCut == 0) || (playerWhoCut == 2)) {
									playedCardValue = allHighestCardValue;  						
									playedCardSuit = allHighestCardSuit;
								} else if  ((playerWhoCut == 1) || (playerWhoCut == 3)) {				//If Opposite team Cuts
									if (trumpCards.isEmpty()) {											//If no trump card, play lowest Card
										playedCardValue = allLowestCardValue;							//Play Lowest Card, since opposite team has highest card on table 
										playedCardSuit = allLowestCardSuit;
									} else {
										for (int i = 0; i < trumpCards.size(); i++) {					//If trump cards that are higher than whats on the table present
											if (trumpCards.get(i) > cutCardValue) {
												playedCardValue = trumpCards.get(trumpCards.size()-1);  //Play trump card just higher (trumpCards are sorted) 
												playedCardSuit = Trump.trumpSuit;
												isTrumpCard = true;
												break;
											} else {
												isTrumpCard = false;
											}
										}
										if (isTrumpCard == false) {										//If no trump card that are higher than whats on the table present
											playedCardValue = allLowestCardValue;						//Play lowest card 
											playedCardSuit = allLowestCardSuit;
										}	
									} 
								}
							}
							
						} else {
							if (playerWhoCut != 99) {													//Check if NOT already CUT
								if ((playerWhoCut == 0) || (playerWhoCut == 2)) {						//If same team has CUT
									playedCardValue = playableCards.get(playableCards.size()-1);		//Play Largest Card
									playedCardSuit = openingSuit;
								} else if  ((playerWhoCut == 1) || (playerWhoCut == 3)) {				//If opposite team has CUT	
									playedCardValue = playableCards.get(0);								//Play Smallest Cart
									playedCardSuit = openingSuit;
								}
							} else {
								//need to check who is playing next (pending)					
								if ((currentHighestPlayer.equals("0")) || (currentHighestPlayer.equals("2"))) {
									playedCardValue = playableCards.get(playableCards.size()-1);  	//Play Largest Card, since same team has highest card on table
									playedCardSuit = openingSuit;
								} else if ((currentHighestPlayer.equals("1")) || (currentHighestPlayer.equals("3"))) {
									playedCardValue = playableCards.get(0);							//Play Lowest Card, since opposite team has highest card on table 								
									playedCardSuit = openingSuit;
								}
							}
						} // End Own Team - 0/2
						
					} else if  ((this.startPlayer == 1) || (this.startPlayer == 3)) {
						//Opposing team
						if (playableCards.isEmpty()){
							//if previous cut by own team... put highest card with points
							//if round is of trumps, check who has highest card, and put points accordingy
							//if not previously cut... open tump & cut.							
							if (playerWhoCut == 99) {													//If NO cut so far				
								if (trumpCards.isEmpty()) {												//If no trump card, play lowest Card
									playedCardValue = allLowestCardValue;								//Play Lowest Card, since opposite team has highest card on table 
									playedCardSuit = allLowestCardSuit;
								} else {
									playedCardValue = trumpCards.get(0);  								//Play lowest trump card to cut 
									playedCardSuit = Trump.trumpSuit;
								} 								
							} else {
								if ((playerWhoCut == 0) || (playerWhoCut == 2)) {
									playedCardValue = allHighestCardValue;  						
									playedCardSuit = allHighestCardSuit;
								} else if  ((playerWhoCut == 1) || (playerWhoCut == 3)) {				//If Opposite team Cuts
									if (trumpCards.isEmpty()) {											//If no trump card, play lowest Card
										playedCardValue = allLowestCardValue;							//Play Lowest Card, since opposite team has highest card on table 
										playedCardSuit = allLowestCardSuit;
									} else {
										for (int i = 0; i < trumpCards.size(); i++) {					//If trump cards that are higher than whats on the table present
											if (trumpCards.get(i) > cutCardValue) {
												playedCardValue = trumpCards.get(trumpCards.size()-1);  //Play trump card just higher (trumpCards are sorted) 
												playedCardSuit = Trump.trumpSuit;
												isTrumpCard = true;
												break;
											} else {
												isTrumpCard = false;
											}
										}
										if (isTrumpCard == false) {										//If no trump card that are higher than whats on the table present
											playedCardValue = allLowestCardValue;						//Play lowest card 
											playedCardSuit = allLowestCardSuit;
										}	
									} 
								}
							}
							
							
						} else {
							//Get smallest & Play Cos different team
							if (playerWhoCut != 99) {													//Check if NOT already CUT
								if ((playerWhoCut == 0) || (playerWhoCut == 2)) {						//If same team has CUT
									playedCardValue = playableCards.get(playableCards.size()-1);		//Play Largest Card
									playedCardSuit = openingSuit;
								} else if  ((playerWhoCut == 1) || (playerWhoCut == 3)) {				//If opposite team has CUT	
									playedCardValue = playableCards.get(0);								//Play Smallest Cart
									playedCardSuit = openingSuit;
								}
							} else {
								//need to check who is playing next (pending)					
								if ((currentHighestPlayer.equals("0")) || (currentHighestPlayer.equals("2"))) {
									playedCardValue = playableCards.get(playableCards.size()-1);  	//Play Largest Card, since same team has highest card on table
									playedCardSuit = openingSuit;
								} else if ((currentHighestPlayer.equals("1")) || (currentHighestPlayer.equals("3"))) {
									playedCardValue = playableCards.get(0);							//Play Lowest Card, since opposite team has highest card on table 								
									playedCardSuit = openingSuit;
								}
							}
						}
					} // End Opposing Team 0/2 against 1/3
					
				} else if ((this.currentPlayer == 1) || (this.currentPlayer == 3)) {
					if ((this.startPlayer == 0) || (this.startPlayer == 2)) {
						//Opposing team
						if (playableCards.isEmpty()){
							//if previous cut by own team... put highest card with points
							//if round is of trumps, check who has highest card, and put points accordingy
							//if not previously cut... open tump & cut.							
							if (playerWhoCut == 99) {													//If NO cut so far				
								if (trumpCards.isEmpty()) {												//If no trump card, play lowest Card
									playedCardValue = allLowestCardValue;								//Play Lowest Card, since opposite team has highest card on table 
									playedCardSuit = allLowestCardSuit;
								} else {
									playedCardValue = trumpCards.get(0);  								//Play lowest trump card to cut 
									playedCardSuit = Trump.trumpSuit;
								} 								
							} else {
								if ((playerWhoCut == 1) || (playerWhoCut == 3)) {
									playedCardValue = allHighestCardValue;  						
									playedCardSuit = allHighestCardSuit;
								} else if  ((playerWhoCut == 0) || (playerWhoCut == 2)) {				//If Opposite team Cuts
									if (trumpCards.isEmpty()) {											//If no trump card, play lowest Card
										playedCardValue = allLowestCardValue;							//Play Lowest Card, since opposite team has highest card on table 
										playedCardSuit = allLowestCardSuit;
									} else {
										for (int i = 0; i < trumpCards.size(); i++) {					//If trump cards that are higher than whats on the table present
											if (trumpCards.get(i) > cutCardValue) {
												playedCardValue = trumpCards.get(trumpCards.size()-1);  //Play trump card just higher (trumpCards are sorted) 
												playedCardSuit = Trump.trumpSuit;
												isTrumpCard = true;
												break;
											} else {
												isTrumpCard = false;
											}
										}
										if (isTrumpCard == false) {										//If no trump card that are higher than whats on the table present
											playedCardValue = allLowestCardValue;						//Play lowest card 
											playedCardSuit = allLowestCardSuit;
										}	
									} 
								}
							}
							
							
						} else {
							//Get smallest & Play Cos different team
							if (playerWhoCut != 99) {													//Check if NOT already CUT
								if ((playerWhoCut == 1) || (playerWhoCut == 3)) {						//If same team has CUT
									playedCardValue = playableCards.get(playableCards.size()-1);		//Play Largest Card
									playedCardSuit = openingSuit;
								} else if  ((playerWhoCut == 0) || (playerWhoCut == 2)) {				//If opposite team has CUT	
									playedCardValue = playableCards.get(0);								//Play Smallest Cart
									playedCardSuit = openingSuit;
								}
							} else {
								//need to check who is playing next (pending)					
								if ((currentHighestPlayer.equals("1")) || (currentHighestPlayer.equals("3"))) {
									playedCardValue = playableCards.get(playableCards.size()-1);  	//Play Largest Card, since same team has highest card on table
									playedCardSuit = openingSuit;
								} else if ((currentHighestPlayer.equals("0")) || (currentHighestPlayer.equals("2"))) {
									playedCardValue = playableCards.get(0);							//Play Lowest Card, since opposite team has highest card on table 								
									playedCardSuit = openingSuit;
								}
							}
						} // End Opposing Team 1/3 against 0/2
						
					} else if ((this.startPlayer == 1) || (this.startPlayer == 3)) {
						//Own team
						if(playableCards.isEmpty()){													//If there are cards of same suit to play
							if (playerWhoCut == 99) {													//If NO cut so far				
								//need to check who is playing next (pending)
								//need to check who has highest card on table (pending)
								//who has highest card on table?
								//if they have current Highest player - check if my highest card > their highest card player. 
								if ((currentHighestPlayer.equals("1")) || (currentHighestPlayer.equals("3"))) {
									playedCardValue = allHighestCardValue;  						//Play Largest Card, since same team has highest card on table
									playedCardSuit = allHighestCardSuit;
								} else if ((currentHighestPlayer.equals("0")) || (currentHighestPlayer.equals("2"))) {
									playedCardValue = allLowestCardValue;							//Play Lowest Card, since opposite team has highest card on table 
									playedCardSuit = allLowestCardSuit;
								}								
							} else {
								if ((playerWhoCut == 1) || (playerWhoCut == 3)) {
									playedCardValue = allHighestCardValue;  						
									playedCardSuit = allHighestCardSuit;
								} else if  ((playerWhoCut == 0) || (playerWhoCut == 2)) {				//If Opposite team Cuts
									if (trumpCards.isEmpty()) {											//If no trump card, play lowest Card
										playedCardValue = allLowestCardValue;							//Play Lowest Card, since opposite team has highest card on table 
										playedCardSuit = allLowestCardSuit;
									} else {
										for (int i = 0; i < trumpCards.size(); i++) {					//If trump cards that are higher than whats on the table present
											if (trumpCards.get(i) > cutCardValue) {
												playedCardValue = trumpCards.get(trumpCards.size()-1);  //Play trump card just higher (trumpCards are sorted) 
												playedCardSuit = Trump.trumpSuit;
												isTrumpCard = true;
												break;
											} else {
												isTrumpCard = false;
											}
										}
										if (isTrumpCard == false) {										//If no trump card that are higher than whats on the table present
											playedCardValue = allLowestCardValue;						//Play lowest card 
											playedCardSuit = allLowestCardSuit;
										}	
									} 
								}
							}
							
						} else {
							if (playerWhoCut != 99) {													//Check if NOT already CUT
								if ((playerWhoCut == 1) || (playerWhoCut == 3)) {						//If same team has CUT
									playedCardValue = playableCards.get(playableCards.size()-1);		//Play Largest Card
									playedCardSuit = openingSuit;
								} else if  ((playerWhoCut == 0) || (playerWhoCut == 2)) {				//If opposite team has CUT	
									playedCardValue = playableCards.get(0);								//Play Smallest Cart
									playedCardSuit = openingSuit;
								}
							} else {
								//need to check who is playing next (pending)					
								if ((currentHighestPlayer.equals("1")) || (currentHighestPlayer.equals("3"))) {
									playedCardValue = playableCards.get(playableCards.size()-1);  	//Play Largest Card, since same team has highest card on table
									playedCardSuit = openingSuit;
								} else if ((currentHighestPlayer.equals("0")) || (currentHighestPlayer.equals("2"))) {
									playedCardValue = playableCards.get(0);							//Play Lowest Card, since opposite team has highest card on table 								
									playedCardSuit = openingSuit;
								}
							}
						} // End Own Team - 1/3

					}
				}
				
			}
			
			if (playedCardValue == 7) {
				playedCardFace = "Seven";
	    	} else if (playedCardValue == 8) {
	    		playedCardFace = "Eight";
	    	} else if (playedCardValue == 9) {
	    		playedCardFace = "Queen";
	    	} else if (playedCardValue == 10) {
	    		playedCardFace = "King";
	    	} else if (playedCardValue == 11) {
	    		playedCardFace = "Ten";
	    	} else if (playedCardValue == 12) {
	    		playedCardFace = "Ace";
	    	} else if (playedCardValue == 13) {
	    		playedCardFace = "Nine";
	    	} else if (playedCardValue == 14) {
	    		playedCardFace = "Jack";
	    	}
			
		Card thrownCard = new Card(playedCardFace,playedCardSuit);
	
		return thrownCard;
	}
	
}