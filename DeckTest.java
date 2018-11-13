import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static org.mockito.Mockito.mock;

public class DeckTest {
	
	@Test
	public void deckShouldBeOfize52() {
		Deck d = new Deck();
		
		int actual = d.size();
		int expected = 52;
		
		assertEquals(expected, actual);
	}

	
	@Test
	public void deckShouldContainCards() {
		Deck d = new Deck();
		
		Card actual = d.cards.get(0);
		Card expected = mock(Card.class);
		
		assertEquals(expected.getClass(), actual.getClass());
	}

}
