import java.util.ArrayList;

public class Deck {

	private int size;
	ArrayList<Card> cards = new ArrayList<>();
	
	public Deck() {
		for(int i = 0; i < 52;  i ++) {
			cards.add(new Card(Card.Denomination.ACE, Card.Suit.SPADES));
			size ++;
		}
	}
	
	public int size() {
		return size;
	}
	
	public Card getTopCard() {
		return cards.get(0);
	}
	
}
