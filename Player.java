import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<>();
	
	public Iterable<Card> showHand() {
		return hand;
	}
	
	public int getSize() {
		return hand.size();
	}
	
	public void dealCard(Card c) {
		if(c != null)
			hand.add(c);
		//Don't want to be forced to handle exceptions every time I call dealCard(). A muted error is fine
	}
}
