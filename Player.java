import java.util.ArrayList;

public class Player {
	
	private int size = 0;
	private ArrayList<Card> hand = new ArrayList<>();
	
	public Iterable<Card> showHand() {
		return hand;
	}
	
	public int getSize() {
		return size;
	}
	
	public void dealCard(Card c ) {
		hand.add(c);
		size ++;
	}
}
