import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trump
{
	List<Card> hand = new ArrayList<Card>();
	public static int trumpCall; 
	public static String trumpFace;
	public static String trumpSuit;
	public static Card trumpCard;
	
    public Trump(List<Card> hands) // constructor
    {
       setTrump(hands);
    } // end constructor
    
	public void setTrump(List<Card> hands) {
	this.hand = hands;
	}
	
	public Card setTrumpCard () {
	trumpCard = new Card( this.trumpFace, this.trumpSuit );
	return trumpCard;
	}
	
	public int setTrumpCall () {
	
	 String[] faces = new String [4];  
	 String[] suits = new String [4];  
	 int[] points = new int [4];
	 int[] values = new int [4];	 
	 int totalpoints = 0;
	 int pointClubs = 0; 	//Number of 28 points with a particular suit
	 int pointHearts = 0; 
	 int pointDiamonds = 0; 
	 int pointSpades = 0;
	 int valueClubs = 0; 	//Total value (corresponding number) with a particular suit
	 int valueHearts = 0; 
	 int valueDiamonds = 0; 
	 int valueSpades = 0;
	 int countClubs = 0; 	//Number of cards with a particular suit
	 int countHearts = 0; 
	 int countDiamonds = 0; 
	 int countSpades = 0;
	 Map<String,Integer> cCountMap = new HashMap<String,Integer>();
	 Map<String,Integer> cPointMap = new HashMap<String,Integer>();
	 Map<String,Integer> cValueMap = new HashMap<String,Integer>();
	 int LowestClubs = 0;
	 int LowestHearts = 0;
	 int LowestDiamonds = 0;
	 int LowestSpades = 0;
	 int bufferTrumpAddOn = 0;
	 int trumpFaceValue = 0;

	 
	 for (int i = 0; i < 4; i++) {
	 faces[i] = this.hand.get(i).getFace(); 
	 points[i] = this.hand.get(i).getPoint();
	 values[i] = this.hand.get(i).getValue();
	 suits[i] = this.hand.get(i).getSuit();
	 
	 if (suits[i].equals("Clubs")) {
	 pointClubs = pointClubs + points[i];
	 valueClubs = valueClubs + values[i];
	 countClubs++;
	 if (countClubs == 1) {
	     LowestClubs = values[i];
	 } else if (countClubs > 1) {
	 if (values[i] < LowestClubs) {
	 LowestClubs = values[i];
	 }
	 }
	                         
	 } else if (suits[i].equals("Hearts")) {
	 pointHearts = pointHearts + points[i];
	 valueHearts = valueHearts + values[i];
	 countHearts++;
	 if (countHearts == 1) {
	 LowestHearts = values[i];
	 } else if (countHearts > 1) {
	 if (values[i] < LowestHearts) {
	 LowestHearts = values[i];
	 }
	 }
	 
	 } else if (suits[i].equals("Diamonds")) {
	 pointDiamonds = pointDiamonds + points[i];
	 valueDiamonds = valueDiamonds + values[i];
	 countDiamonds++;
	 if (countDiamonds == 1) {
	 LowestDiamonds = values[i];
	 } else if (countDiamonds > 1) {
	 if (values[i] < LowestDiamonds) {
	 LowestDiamonds = values[i];
	 }
	 }
	 
	 } else if (suits[i].equals("Spades")) {
	 pointSpades = pointSpades + points[i];
	 valueSpades = valueSpades + values[i];
	 countSpades++;
	 if (countSpades == 1) {
	 LowestSpades = values[i];
	 } else if (countSpades > 1) {
	 if (values[i] < LowestSpades) {
	 LowestSpades = values[i];
	 }
	 }
	 
	 }
	 
	 totalpoints = totalpoints + points[i]; 
	 }

	 cValueMap.put("Clubs",valueClubs);
	 cValueMap.put("Hearts",valueHearts);
	 cValueMap.put("Diamonds",valueDiamonds);
	 cValueMap.put("Spades",valueSpades);
	 
	 cPointMap.put("Clubs",pointClubs);
	 cPointMap.put("Hearts",pointHearts);
	 cPointMap.put("Diamonds",pointDiamonds);
	 cPointMap.put("Spades",pointSpades);
	 
	 cCountMap.put("Clubs",countClubs);
	 cCountMap.put("Hearts",countHearts);
	 cCountMap.put("Diamonds",countDiamonds);
	 cCountMap.put("Spades",countSpades);
	   
	 cValueMap = MapUtil.sortByValue( cValueMap );
	 cPointMap = MapUtil.sortByValue( cPointMap );
	 cCountMap = MapUtil.sortByValue( cCountMap );
        
	// Find Which Suit to keep as trump... If 2 or more of total value/ total points/ total number of any suit - then that is trump.  
        if ((cValueMap.keySet().toString().equals(cPointMap.keySet().toString())) && (cPointMap.keySet().toString().equals(cCountMap.keySet().toString()))
        	&& (cValueMap.keySet().toString().equals(cCountMap.keySet().toString()))) {
        	Trump.trumpSuit = cValueMap.keySet().toArray()[3].toString();
        } else if ((cValueMap.keySet().toString().equals(cPointMap.keySet().toString())) && (cValueMap.keySet().toString().equals(cCountMap.keySet().toString()))) {
        	Trump.trumpSuit = cValueMap.keySet().toArray()[3].toString();
        } else if ((cPointMap.keySet().toString().equals(cValueMap.keySet().toString())) && (cPointMap.keySet().toString().equals(cCountMap.keySet().toString()))) {
        	Trump.trumpSuit = cPointMap.keySet().toArray()[3].toString();
        } else if ((cCountMap.keySet().toString().equals(cValueMap.keySet().toString())) && (cCountMap.keySet().toString().equals(cPointMap.keySet().toString()))) {
        	Trump.trumpSuit = cCountMap.keySet().toArray()[3].toString();
        } else {
        	Trump.trumpSuit = cValueMap.keySet().toArray()[3].toString();
        }
        
        // Add buffer to Trump Call Score
    	if (cPointMap.get(Trump.trumpSuit) < 3) {
    	bufferTrumpAddOn = 0;
    	} else if ((cPointMap.get(Trump.trumpSuit) > 3) && (cPointMap.get(Trump.trumpSuit) < 6)) {
    	bufferTrumpAddOn = 1;
    	} else if ((cPointMap.get(Trump.trumpSuit) > 6) && (cPointMap.get(Trump.trumpSuit) < 9)) {
    	bufferTrumpAddOn = 2;
    	} else if ((cPointMap.get(Trump.trumpSuit) > 9) && (cPointMap.get(Trump.trumpSuit) < 12)) {
    	bufferTrumpAddOn = 3;
    	}
    	System.out.println("");
    	
        // Find Lowest Card of Trump Suit to hide/fold
        if (Trump.trumpSuit.equals("Clubs")) {
        	trumpFaceValue = LowestClubs;
        } else if (Trump.trumpSuit.equals("Hearts")) {
        	trumpFaceValue = LowestHearts;
        } else if (Trump.trumpSuit.equals("Diamonds")) {
        	trumpFaceValue = LowestDiamonds;
        } else if (Trump.trumpSuit.equals("Spades")) {
        	trumpFaceValue = LowestSpades;
        }
        
        // Convert trumpFaceValue to Face
        switch (trumpFaceValue) {
        	case 7: Trump.trumpFace = "Seven" ;
        			break;
        	case 8: Trump.trumpFace = "Eight" ;
        			break;
        	case 9: Trump.trumpFace = "Queen" ;
        			break;
        	case 10: Trump.trumpFace = "King" ;
        			break;
        	case 11: Trump.trumpFace = "Ten" ;
        			break;
        	case 12: Trump.trumpFace = "Ace" ;
        			break;
        	case 13: Trump.trumpFace = "Nine" ;
        			break;
        	case 14: Trump.trumpFace = "Jack" ;
        			break;
        }
       	 
	 switch (totalpoints) {
	 	case 0: Trump.trumpCall = 14 + bufferTrumpAddOn;
	 	break;
	 	case 1: Trump.trumpCall = 14 + bufferTrumpAddOn;
	        	break;
	        case 2: Trump.trumpCall = 14 + bufferTrumpAddOn;
	            break;
	        case 3: Trump.trumpCall = 14 + bufferTrumpAddOn;
	        	break;
	        case 4: Trump.trumpCall = 14 + bufferTrumpAddOn;
	        	break;
	        case 5: Trump.trumpCall = 14 + bufferTrumpAddOn;
	    		break;
	        case 6: Trump.trumpCall = 14 + bufferTrumpAddOn;
	    		break;
	        case 7: Trump.trumpCall = 15 + bufferTrumpAddOn;
	    		break;
	        case 8: Trump.trumpCall = 15 + bufferTrumpAddOn;
	    		break;
	        case 9: Trump.trumpCall = 15 + bufferTrumpAddOn;
	 			break;
	        case 10: Trump.trumpCall = 15 + bufferTrumpAddOn;
	        	break;
	        case 11: Trump.trumpCall = 15 + bufferTrumpAddOn;
	            break;
	        case 12: Trump.trumpCall = 15 + bufferTrumpAddOn;
	    		break;	
	 }	 
	
	 return Trump.trumpCall;

	}
  
} // end Hand class