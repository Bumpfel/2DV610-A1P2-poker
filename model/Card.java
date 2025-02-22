package model;

public class Card {

	private Denomination denomination;
	private Suit suit;
	
	public enum Denomination { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };
	public enum Suit { HEARTS, SPADES, DIAMONDS, CLUBS }; 
	
	public Card(Denomination newDenomination, Suit newSuit) {
		denomination = newDenomination;
		suit = newSuit;
	}
	
	public Denomination getDenomination() {
		return denomination;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Card) {
			Card card2 = (Card) obj;
			if(denomination == card2.getDenomination() && suit == card2.getSuit())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String denom = denomination.toString();
		String str = denom.substring(0, 1) + denom.substring(1).toLowerCase();
		str += " of " + suit.toString().toLowerCase();
		
		return str;
	}
}
