public class Card {

	private Denomination denomination;
	private Suit suit;
	
	public enum Denomination { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };
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
}
