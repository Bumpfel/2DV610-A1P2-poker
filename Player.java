import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<>();
	
	public enum Score { HIGH_CARD, PAIR };
	
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
	
	public void clearHand() {
		hand.clear();
	}
	
	public Score getScore() {
		int values[] = new int[13];
		for(Card c : hand) {
			int valueIndex = c.getDenomination().ordinal();
			values[valueIndex] ++;
		}
		Arrays.sort(values);

		if(values[12] == 2) {
			return Score.PAIR;
		}
		return Score.HIGH_CARD;
	}
	
}
