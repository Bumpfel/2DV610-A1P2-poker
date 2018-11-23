package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<>();
	
	public Deck() {
		init();
	}
	
	public void init() {
		for(Card.Suit suit : Card.Suit.values()) {
			for(Card.Denomination denomination : Card.Denomination.values()) {
				Card c = createCard(denomination, suit);
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
		return cards.remove(0);
	}
	
	public boolean isEmpty() {
		return cards.size() == 0;
	}
	
	public boolean contains(Card c) {
		for(Card card : cards) {
			if(card.equals(c))
				return true;
		}
		return false;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public void reset() {
		cards.clear();
		init();
	}
}
