import static org.mockito.Mockito.*;

import org.junit.Test;

public class PokerTest {

	@Test
	public void cardsShouldHaveADenomination() {
		Card c = new Card(Denomination.ACE);
		
		Denomination expected = Denomination.ACE;
		Denomination actual = c.denomination;
		
		assertEquals(expected, actual);
	}
	
}
