import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class PlayerTest {

	Player sut;
	
	@Test
	public void showHandShouldReturnIterable() {
		sut = new Player();
		
		assertTrue(sut.showHand() instanceof Iterable);
	}
	
	@Test
	public void shouldBeAbleToGetSizeOfHand() {
		sut = new Player();
		
		int actual = sut.getSize();
		int expected = 0;
		assertEquals(expected, actual);
		
	}

	@Test
	public void receivingACardShouldIncrementHandSize() {
		sut = new Player();
		Card mockCard = mock(Card.class);
		
		sut.dealCard(mockCard); // I name this dealCard since I figure I'll make a Game class that calls this method, and it wouldn't make sense semantically to call player.receiveCard()
		
		int expected = 1;
		int actual = sut.getSize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void dealCardShouldAddCardToHand() {
		sut = new Player();
		Card mockCard = mock(Card.class);
		
		sut.dealCard(mockCard);
		
		int actualCards = 0;
		Iterable<Card> hand = sut.showHand();
		for(Card c : hand) {
			actualCards ++;
		}
		int expectedCards = 1;
		
		assertEquals(expectedCards, actualCards);
	}
	
	@Test
	public void shouldNotBeAbleToDealNullObject() {
		sut = new Player();
		
		sut.dealCard(null);
		int expected = 0;
		int actual = sut.getSize();
		
		assertEquals(expected, actual);
	}
}
