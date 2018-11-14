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

}
