import java.util.ArrayList;
import java.util.List;

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
	
    
} // end Hand class