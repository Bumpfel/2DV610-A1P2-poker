import java.util.ArrayList;

public class Deck {

	ArrayList<Card> cards = new ArrayList<>();
	
	public Deck() {
		init();
	}
	
	void init() {
		for(Card.Suit suit : Card.Suit.values()) {
			for(Card.Denomination denomination : Card.Denomination.values()) {
				Card c = createCard(denomination, suit);
				cards.add(c);
			}
		}
	}
	
	Card createCard(Card.Denomination d, Card.Suit s) {
		return new Card(d, s);
	}
	
	public int size() {
		return cards.size();
	}
	
	public Card getTopCard() {
		return cards.remove(0);
	}
	
}
