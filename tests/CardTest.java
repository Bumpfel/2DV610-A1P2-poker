package tests;

import model.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void cardsShouldHaveADenomination() {
		Card sut = new Card(Card.Denomination.ACE, Card.Suit.SPADES);
		
		Card.Denomination expected = Card.Denomination.ACE;
		Card.Denomination actual = sut.getDenomination();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void cardsShouldHaveASuit() {
		Card sut = new Card(Card.Denomination.ACE, Card.Suit.SPADES);
		
		Card.Suit actual = sut.getSuit();
		Card.Suit expected = Card.Suit.SPADES;

		assertEquals(expected, actual);
	}

	@Test
	public void cardsWithTheSameDenominationAndSuitShouldCountAsEqual() {
		Card sut = new Card(Card.Denomination.ACE, Card.Suit.SPADES);
		Card sut2 = new Card(Card.Denomination.ACE, Card.Suit.SPADES);
		Card sut3 = new Card(Card.Denomination.ACE, Card.Suit.HEARTS);
		
		assertTrue(sut.equals(sut2));
		
		assertFalse(sut.equals(sut3));
	}
	
}
