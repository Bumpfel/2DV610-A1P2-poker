import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PokerTest {

	@Test
	public void cardsShouldHaveADenomination() {
		Card c = new Card(Card.Denomination.ACE, Card.Suit.SPADES);
		
		Card.Denomination expected = Card.Denomination.ACE;
		Card.Denomination actual = c.getDenomination();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void cardsShouldHaveASuit() {
		Card c = new Card(Card.Denomination.ACE, Card.Suit.SPADES);
		
		Card.Suit actual = c.getSuit();
		Card.Suit expected = Card.Suit.SPADES;

		assertEquals(expected, actual);
	}
	
	@Test
	public void deckShouldHave52Cards() {
		Deck d = new Deck();
		
		int actual = d.size();
		int expected = 52;
		
		assertEquals(expected, actual);
	}
	
	
}
