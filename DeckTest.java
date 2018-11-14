import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
	
	@Test
	public void gettinACardFromDeckShouldDecrementDeckSize() {
		Deck sut = new Deck();
		sut.getTopCard();

		int expected = 51;
		int actual = sut.size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnTrueIfDeckIsEmpty() {
		Deck sut = new Deck();
		
		assertFalse(sut.isEmpty());
		
		for(int i = 0; i < 52; i ++) {
			sut.getTopCard();
		}
		
		assertTrue(sut.isEmpty());
	}
	
	@Test
	public void shouldReturnTrueIfCardIsInDeck() {
		Deck sut = new Deck();
				
		Card mockAceOfSpades = mock(Card.class);
		when(mockAceOfSpades.getDenomination()).thenReturn(Card.Denomination.ACE);
		when(mockAceOfSpades.getSuit()).thenReturn(Card.Suit.SPADES);
		
		assertTrue(sut.contains(mockAceOfSpades));
		
		while(!sut.isEmpty()) {
			sut.getTopCard();
		}

		assertFalse(sut.contains(mockAceOfSpades));
	}
	
	@Test
	public void shouldShuffleCardsInDeck() {
		Deck sut1 = new Deck();
		Deck sut2 = new Deck();
		
		ArrayList<Card> list1 = new ArrayList<>();
		ArrayList<Card> list2 = new ArrayList<>();
		
		sut1.shuffle();
		sut2.shuffle();
		
		while(!sut1.isEmpty() && !sut2.isEmpty()) {
			list1.add(sut1.getTopCard());
			list2.add(sut2.getTopCard());
		}
		assertNotEquals(list1, list2); // Not 100% water tight, but extremely unlikely to fail. 1 in 52 factorial, I believe
	}

}
