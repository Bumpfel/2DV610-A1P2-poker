import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import org.junit.Test;

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
		
		Card actual = d.getTopCard();
		Card expected = mock(Card.class);
		
		assertEquals(expected.getClass().getSuperclass(), actual.getClass());
	}

}
