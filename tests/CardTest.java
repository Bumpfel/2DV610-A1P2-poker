package tests;

import model.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

	private Card sut;
	
	@Before
	public void setUp() {
		sut = makeAoS();
	}
	
	@Test
	public void cardsShouldHaveADenomination() {
		Card.Denomination expected = Card.Denomination.ACE;
		Card.Denomination actual = sut.getDenomination();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void cardsShouldHaveASuit() {
		Card.Suit actual = sut.getSuit();
		Card.Suit expected = Card.Suit.SPADES;

		assertEquals(expected, actual);
	}

	@Test
	public void cardsWithTheSameDenominationAndSuitShouldCountAsEqual() {
		Card sut2 = makeAoS();
		Card sut3 = new Card(Card.Denomination.ACE, Card.Suit.HEARTS);
		
		assertTrue(sut.equals(sut2));
		
		assertFalse(sut.equals(sut3));
	}
	
	@Test
	public void shouldReturnProperToString() {
		Card sut2 = new Card(Card.Denomination.SEVEN, Card.Suit.HEARTS);
		
		String expected = "Ace of spades";
		String actual = sut.toString();
		assertEquals(expected, actual);
		
		expected = "Seven of hearts";
		actual = sut2.toString();
		assertEquals(expected, actual);
	}
	
	private Card makeAoS() {
		return new Card(Card.Denomination.ACE, Card.Suit.SPADES);
	}
}
