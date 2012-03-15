import java.util.ArrayList;
import java.util.List;

public class Hand 
{
	List<Card> hand = new ArrayList<Card>();
    
    private int[] faces = new int [8];    
    // ignore index 0, Sevens = 7, Eights = 8, Nines = 9, Tens = 10, Jacks = 11, Queens = 12, Kings = 13, Aces = 14
    private int[] suits = new int [4];  
    // clubs = 0, diamonds = 1, hearts = 2, spades = 3
    
    public Hand(List<Card> cards) // constructor
    {
       setHand(cards);
    } // end constructor
    
    public void show()
    {
    	for (Card temp : hand) {
    		System.out.print(temp.getFace()+" of "+temp.getSuit()+", ");
    	}
    } // end show method
    
    public List<Card> getHand() {
		return hand;
	}
    
    public Card getHandCard(int index) {
		return hand.get(index);
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public String getFaces(int item) {
		return hand.get(item).getFace();
	}
	
	public int getPoints(int item) {
		
		 String faceValue = hand.get(item).getFace();
		 int value = 0;
		 if (faceValue.equals("Jack")) {
			 value = 3;
		 } else if  (faceValue.equals("Nine")) {
			 value = 2;
		 } else if  (faceValue.equals("Ace")) {
			 value = 1;
		 } else if  (faceValue.equals("Ten")) {
			 value = 1;
		 }
		 
		 return value;
	}

	public void setFaces(int[] faces) {
		this.faces = faces;
	}
    
	public String getSuits(int item) {
		return hand.get(item).getSuit();
	}

	public void setSuits(int[] suits) {
		this.suits = suits;
	}
	
    
} // end Hand class