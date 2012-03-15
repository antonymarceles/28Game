
// Fig. 7.9: Card.java
// Card class represents a playing card.
public class Card 
{
   private String face; // face of card ("Ace", "Deuce", ...)
   private String suit; // suit of card ("Hearts", "Diamonds", ...)

   // two-argument constructor initializes card's face and suit
   public Card( String cardFace, String cardSuit )
   {
      face = cardFace; // initialize face of card
      suit = cardSuit; // initialize suit of card
   } // end two-argument Card constructor

   // return String representation of Card
   public String toString() 
   { 
      return face + " of " + suit;
   } // end method toString

   // getter added per homework #3 part step 1 request
   public String getFace() 
   {
	return face;
   }
   
   public int getValue() 
   {
	    int faceValue = 0;
		if (this.getFace() == "Seven") {
    		faceValue = 7;
    	} else if (this.getFace() == "Eight") {
    		faceValue = 8;
    	} else if (this.getFace() == "Queen") {
    		faceValue = 9;
    	} else if (this.getFace() == "King") {
    		faceValue = 10;
    	} else if (this.getFace() == "Ten") {
    		faceValue = 11;
    	} else if (this.getFace() == "Ace") {
    		faceValue = 12;
    	} else if (this.getFace() == "Nine") {
    		faceValue = 13;
    	} else if (this.getFace() == "Jack") {
    		faceValue = 14;
    	}
		
		return faceValue;
	}
   
	public int getPoint() {
		 int value = 0;
		 if (this.getFace().equals("Jack")) {
			 value = 3;
		 } else if  (this.getFace().equals("Nine")) {
			 value = 2;
		 } else if  (this.getFace().equals("Ace")) {
			 value = 1;
		 } else if  (this.getFace().equals("Ten")) {
			 value = 1;
		 } 
		 
		 return value;
	}

   // getter added per homework #3 part step 1 request 
   public String getSuit() 
   {
	return suit;
   }

} // end class Card
