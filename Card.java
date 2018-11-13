public class Card {

	Denomination denomination;
	
	public enum Denomination { ACE };

	public Card(Denomination d) {
		denomination = Denomination.ACE;
	}

}
