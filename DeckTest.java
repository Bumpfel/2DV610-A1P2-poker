import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import org.junit.Test;

public class DeckTest {
	
	@Test
	public void deckShouldBeOfize52() {
		Deck sut = new Deck();
		
		int actual = sut.size();
		int expected = 52;
		
		assertEquals(expected, actual);
	}

	
	@Test
	public void deckShouldContainCards() {
		Deck sut = new Deck();
		
		Card actual = sut.getTopCard();
		Card expected = mock(Card.class);
		
		assertEquals(expected.getClass().getSuperclass(), actual.getClass());
	}

}
