import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PokerTest {

	@Test
	public void cardsShouldHaveADenomination() {
		Card c = new Card(Card.Denomination.ACE);
		
		Card.Denomination expected = Card.Denomination.ACE;
		Card.Denomination actual = c.denomination;
		
		assertEquals(expected, actual);
	}
	
}
