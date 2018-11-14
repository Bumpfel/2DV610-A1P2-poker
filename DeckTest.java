import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
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
	
	@Test
	public void deckShouldContain13CardsOfEachSuit() {
		Deck sutSpy = spy(Deck.class);
		
		sutSpy.init();
		
		verify(sutSpy, times(13)).createCard(any(), eq(Card.Suit.HEARTS));
		verify(sutSpy, times(13)).createCard(any(), eq(Card.Suit.SPADES));
		verify(sutSpy, times(13)).createCard(any(), eq(Card.Suit.DIAMONDS));
		verify(sutSpy, times(13)).createCard(any(), eq(Card.Suit.CLUBS));
	}
	
	@Test
	public void deckShouldContain4CardsOfEachDenomination() {
		Deck sutSpy = spy(Deck.class);
		
		sutSpy.init();

		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.ACE), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.TWO), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.THREE), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.FOUR), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.FIVE), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.SIX), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.SEVEN), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.EIGHT), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.NINE), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.TEN), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.JACK), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.QUEEN), any());
		verify(sutSpy, times(4)).createCard(eq(Card.Denomination.KING), any());
	}

}
