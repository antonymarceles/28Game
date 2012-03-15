import java.util.ArrayList;
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
		 List<Card> playableCards = new ArrayList<Card> ();
		 Map<Integer,Integer> cardsPlayedPlayers = new HashMap<Integer,Integer> (); 
		 
		// System.out.println(Trump.trumpCard);
		
			if (this.cardsOnTable == null) { 

			} else {
				openingSuit = cardsOnTable.get(0).getSuit();
				
				//find the cards that are playable of the same suit
				for (int i = 0; i < this.cardsOnHand.size(); i++) {
					suits[i] = this.cardsOnHand.get(i).getSuit();	
					if (suits[i] == openingSuit) {
						playableCards.add(this.cardsOnHand.get(i));
					}
				}
				
				
				//find the Card played by each player
				for (int i = 0; i < this.cardsOnTable.size(); i++) {
					values[i] = this.cardsOnTable.get(i).getValue();
					cardsPlayedPlayers.put((this.startPlayer + 0), values[i]);
					System.out.println(cardsPlayedPlayers.get((this.startPlayer + 0)));
				}
				
				if ((this.currentPlayer == 0) || (this.currentPlayer == 2)) {
					if ((this.startPlayer == 0) || (this.startPlayer == 2)) {
						//Own team
						if(playableCards != null){
							//GetLargest & Play Cos same team
						} else if (playableCards == null){
							//if previous cut... check if trump present... if present... over cut...
							//if not present... lowest non-relevant card.
						}
						
					} else if  ((this.startPlayer == 1) || (this.startPlayer == 3)) {
						//Opposing team
						if(playableCards != null){
							//Get smallest & Play Cos different team
						} else if (playableCards == null){
							//if previous cut by own team... put highest card with points
							//if round is of trumps, check who has highest card, and put points accordingy
							//if not previously cut... open tump & cut.
						}
					}
				} else if ((this.currentPlayer == 1) || (this.currentPlayer == 3)) {
					if ((this.startPlayer == 0) || (this.startPlayer == 2)) {
						//Opposing team
						if(playableCards != null){
							//Get smallest & Play Cos different team
						} else if (playableCards == null){
							//if previous cut by own team... put highest card with points
							//if round is of trumps, check who has highest card, and put points accordingy
							//if not previously cut... open tump & cut.
						}
					} else if  ((this.startPlayer == 1) || (this.startPlayer == 3)) {
						if(playableCards != null){
							//GetLargest & Play Cos same team
						} else if (playableCards == null){
							//if previous cut... check if trump present... if present... over cut...
							//if not present... lowest non-relevant card.
						}
						
					}
				}
				
			}
			
		Card thrownCard = new Card("Ten","Spades");
		return thrownCard;
	}
	
}
