import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table 
{
	List<Card> table = new ArrayList<Card>();
    
    private int[] faces = new int [8];    
    // ignore index 0, Sevens = 7, Eights = 8, Nines = 9, Tens = 10, Jacks = 11, Queens = 12, Kings = 13, Aces = 14
    private int[] suits = new int [4];  
    // clubs = 0, diamonds = 1, hearts = 2, spades = 3
    
    public Table(List<Card> cards) // constructor
    {
       setTable(cards);
    } // end constructor
    
    public void show()
    {
    	for (Card temp : table) {
    		System.out.println(temp.getFace()+" of "+temp.getSuit());
    	}
    } // end show method
    
    public List<Card> getTable() {
		return table;
	}

	public void setTable(List<Card> table) {
		this.table = table;
	}

	public String getFaces(int item) {
		return table.get(item).getFace();
	}

	public int getPoints(int item) {
		
		 String faceValue = table.get(item).getFace();
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
		return table.get(item).getSuit();
	}
	
	public void setSuites(int[] suits) {
		this.suits = suits;
	}
	
	public int findTablePoints() {		
		int points = 0;
		for (int i = 0; i<this.table.size(); i++) {
			points = points + this.table.get(i).getPoint();
		}
		return points;
	}
	
	public int findWinningPlayer(int startPlayer) {
		int winningPlayer = 99;
		String currentWinner = "";
		String openingSuit = this.table.get(0).getSuit();
		
		Map<Integer,Integer> trumpCards = new HashMap<Integer,Integer> ();
		Map<Integer,Integer> oSuitCards = new HashMap<Integer,Integer> ();
		Map<Integer,Integer> otherCards = new HashMap<Integer,Integer> ();

		for (int i = 0; i<this.table.size(); i++) {
			if (this.table.get(i).getSuit() == Trump.trumpSuit) {
				trumpCards.put(i, this.table.get(i).getValue());
			} else if (this.table.get(i).getSuit() == openingSuit) {
				oSuitCards.put(i, this.table.get(i).getValue());
			} else {
				otherCards.put(i, this.table.get(i).getValue());
			}
		}
		
		if (trumpCards.size() == 0) {
			if (oSuitCards.size() == 0) {
				otherCards = MapUtil.sortByValue( otherCards );			//Find Player with higest Card on table. 
				currentWinner = otherCards.keySet().toArray()[otherCards.size()-1].toString();
			} else {
				oSuitCards = MapUtil.sortByValue( oSuitCards );			//Find Player with higest Card on table. 
				currentWinner = oSuitCards.keySet().toArray()[oSuitCards.size()-1].toString();
			}
		} else {
				trumpCards = MapUtil.sortByValue( trumpCards );			//Find Player with higest Card on table. 
				currentWinner = trumpCards.keySet().toArray()[trumpCards.size()-1].toString();		
		}
		
		if (currentWinner.equals("0")) {
			winningPlayer = 0;
		} else if (currentWinner.equals("1")) {
			winningPlayer = 1;
		} else if (currentWinner.equals("2")) {
			winningPlayer = 2;
		} else if (currentWinner.equals("3")) {
			winningPlayer = 3;
		}
		
		winningPlayer = startPlayer+winningPlayer+1;
		 
		if (winningPlayer>3) {
			winningPlayer = winningPlayer - 4;
		} 
		
		return winningPlayer;
	}
	
    
} // end Hand class