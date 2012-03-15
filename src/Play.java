import java.util.List;


public class Play {

	List<Card> cardsOnTable;
	List<Card> cardsOnHand;
    
	public Play(List<Card> cardsOnTable, List<Card> cardsOnHand) // constructor
    {
		this.cardsOnTable = cardsOnTable;
		this.cardsOnHand = cardsOnHand;
    } // end constructor
    
	public Card throwCard() {
		
		 String[] faces = new String [4];  
		 String[] suits = new String [4];  
		 int[] points = new int [4];
		 int[] values = new int [4];	
		 
		 System.out.println(Trump.trumpCard);
		
		if (this.cardsOnTable == null) {
			for (int i = 0; i < this.cardsOnHand.size(); i++) {
				 values[i] = this.cardsOnHand.get(i).getValue();
				 suits[i] = this.cardsOnHand.get(i).getSuit();
				 
				 tets;
			}
		} else {
			
		}
		
		Card thrownCard = new Card("Ten","Spades");
		return thrownCard;
	}
	
}
