public class Card {

	private Denomination denomination;
	Suit suit;
	
	public enum Denomination { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KNIGHT, QUEEN, KING };
	public enum Suit { HEARTS, SPADES, DIAMONDS, CLOVES }; 
	
	public Card(Denomination d, Suit s) {
		denomination = d;
		suit = s;
	}
	
	
	public Denomination getDenomination() {
		return denomination;
	}

}
