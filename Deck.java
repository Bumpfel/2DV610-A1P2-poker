import java.util.ArrayList;

public class Deck {

	ArrayList<Card> cards = new ArrayList<>();
	
	public Deck() {
		init();
	}
	
	public void init() {
		for(Card.Suit suit : Card.Suit.values()) {
			for(int i = 0; i < 13;  i ++) {
				Card c = createCard(Card.Denomination.ACE, suit);
				cards.add(c);
			}
		}
	}
	
	public Card createCard(Card.Denomination d, Card.Suit s) {
		return new Card(d, s);
	}
	
	public int size() {
		return cards.size();
	}
	
	public Card getTopCard() {
		return cards.get(0);
	}
	
}
