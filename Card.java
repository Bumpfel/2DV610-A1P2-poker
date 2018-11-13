public class Card {

	private Denomination denomination;
	
	public enum Denomination { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KNIGHT, QUEEN, KING };

	public Card(Denomination d) {
		denomination = d;
	}
	
	
	public Denomination getDenomination() {
		return denomination;
	}

}
