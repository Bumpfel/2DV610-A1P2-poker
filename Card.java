public class Card {

	private Denomination denomination;
	private Suit suit;
	
	public enum Denomination { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KNIGHT, QUEEN, KING };
	public enum Suit { HEARTS, SPADES, DIAMONDS, CLOVES }; 
	
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
