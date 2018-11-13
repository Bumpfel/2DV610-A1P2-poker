import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeckTest {
	
	@Test
	public void deckShouldBeOfize52() {
		Deck d = new Deck();
		
		int actual = d.size();
		int expected = 52;
		
		assertEquals(expected, actual);
	}

}
