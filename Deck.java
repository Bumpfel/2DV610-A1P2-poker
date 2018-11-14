import java.util.ArrayList;

public class Deck {

	ArrayList<Card> cards = new ArrayList<>();
	
	public Deck() {
		for(int i = 0; i < 52;  i ++) {
			cards.add(new Card(Card.Denomination.ACE, Card.Suit.SPADES));
		}
	}
	
	public int size() {
		return cards.size();
	}
	
	public Card getTopCard() {
		return cards.get(0);
	}
	
}
