import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<>();
	
	public Iterable<Card> showHand() {
		return hand;
	}
	
	public int getSize() {
		return 0;
	}
}
