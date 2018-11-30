package tests;

import model.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {
	
	private String name = "";
	
	@Test
	public void showHandShouldReturnIterable() {
		Player sut = new Player(name);
		
		assertTrue(sut.getHand() instanceof Iterable);
	}
	
	@Test
	public void shouldBeAbleToGetSizeOfHand() {
		Player sut = new Player(name);
		
		int actual = sut.getHandSize();
		int expected = 0;
		assertEquals(expected, actual);
		
	}

	@Test
	public void receivingACardShouldIncrementHandSize() {
		Player sut = new Player(name);
		Card mockCard = mock(Card.class);
		
		sut.dealCard(mockCard); // I name this dealCard since I figure I'll make a Game class that calls this method, and it wouldn't make sense semantically to call player.receiveCard()
		
		int expected = 1;
		int actual = sut.getHandSize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void dealCardShouldAddCardToHand() {
		Player sut = new Player(name);
		Card mockCard = mock(Card.class);
		
		sut.dealCard(mockCard);
		
		int actualCards = 0;
		Iterable<Card> hand = sut.getHand();
		
		for(Card c : hand) {
			actualCards ++;
		}
		int expectedCards = 1;
		
		assertEquals(expectedCards, actualCards);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotBeAbleToDealNullObject() {
		Player sut = new Player(name);
		
		sut.dealCard(null);
	}
	
	@Test
	public void shouldBeAbleToClearHand() {
		Player sut = new Player(name);
		
		Card mockCard = mock(Card.class);
		
		sut.dealCard(mockCard);
		sut.dealCard(mockCard);
		sut.clearHand();
		
		int expected = 0;
		int actual = sut.getHandSize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDeterminePair() {
		Player sut = new Player(name);
		
		Card mockCard = mockAoS();
		
		sut.dealCard(mockCard);
		sut.dealCard(mockCard); // dealing the same card won't happen when there is a real deck, so I don't need to test for it
		
		Player.Score expected = Player.Score.PAIR;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineHighCard() {
		Player sut = new Player(name);
		
		mockAndDealCard(sut, Card.Denomination.TWO, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.THREE, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.FOUR, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.FIVE, Card.Suit.CLUBS);
		
		Player.Score expected = Player.Score.HIGH_CARD;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineThreeOfAKind() {
		Player sut = new Player(name);
		
		Card mockCard = mockAoS();
		
		mockAndDealCard(sut, Card.Denomination.TWO, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.THREE, Card.Suit.CLUBS);
		sut.dealCard(mockCard);
		sut.dealCard(mockCard);
		sut.dealCard(mockCard);

		
		Player.Score expected = Player.Score.THREE_OF_A_KIND;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineTwoPair() {
		Player sut = new Player(name);
		
		Card mockCard1 = mockAoS();
		Card mockCard2 = mock7oH();
		
		mockAndDealCard(sut, Card.Denomination.FIVE, Card.Suit.CLUBS);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard2);
		
		Player.Score actual = sut.getScore();
		Player.Score expected = Player.Score.TWO_PAIR;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineStraight() {
		Player sut = new Player(name);
		
		mockAndDealCard(sut, Card.Denomination.ACE, Card.Suit.SPADES);
		mockAndDealCard(sut, Card.Denomination.TWO, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.THREE, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.FOUR, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.FIVE, Card.Suit.CLUBS);
		
		Player.Score expected = Player.Score.STRAIGHT;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
				
		//reset
		sut = new Player(name);
		
		mockAndDealCard(sut, Card.Denomination.ACE, Card.Suit.SPADES);
		mockAndDealCard(sut, Card.Denomination.KING, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.QUEEN, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.JACK, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.TEN, Card.Suit.CLUBS);
		
		actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineFlush() {
		Player sut = new Player(name);
		
		mockAndDealCard(sut, Card.Denomination.ACE, Card.Suit.SPADES);
		mockAndDealCard(sut, Card.Denomination.TWO, Card.Suit.SPADES);
		mockAndDealCard(sut, Card.Denomination.JACK, Card.Suit.SPADES);
		mockAndDealCard(sut, Card.Denomination.FOUR, Card.Suit.SPADES);
		mockAndDealCard(sut, Card.Denomination.FIVE, Card.Suit.SPADES);
		
		Player.Score expected = Player.Score.FLUSH;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineFullHouse() {
		Player sut = new Player(name);
		
		Card mockCard1 = mockAoS();
		Card mockCard2 = mock7oH();
		
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard2);
		
		Player.Score expected = Player.Score.FULL_HOUSE;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineFourOfAKind() {
		Player sut = new Player(name);
		
		Card mockCard1 = mockAoS();
		Card mockCard2 = mock7oH();
		
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		
		Player.Score expected = Player.Score.FOUR_OF_A_KIND;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineStraightFlush() {
		Player sut = new Player(name);
		
		mockAndDealCard(sut, Card.Denomination.TWO, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.THREE, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.FOUR, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.FIVE, Card.Suit.CLUBS);
		mockAndDealCard(sut, Card.Denomination.SIX, Card.Suit.CLUBS);
		
		Player.Score expected = Player.Score.STRAIGHT_FLUSH;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldGetPlayerName() {
		String name = "Johnny";
		Player sut = new Player(name);
	
		String expected = name;
		String actual = sut.getName();
		assertEquals(expected, actual);

		name = "Susie";
		sut = new Player(name);
		
		expected = name;
		actual = sut.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldRemoveCardFromHand() {
		Player sut = new Player(name);
		
		Card mockCard = mockAoS();
		sut.dealCard(mockCard);
		sut.removeCard(mockCard);
		
		int expected = 0;
		int actual = sut.getHandSize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldSortByDenomination() {
		Player sut = new Player(name);
		
		Card mockCard1 = mockCard(Card.Denomination.TWO, Card.Suit.CLUBS);
		Card mockCard2 = mockCard(Card.Denomination.THREE, Card.Suit.CLUBS);
		Card mockCard3 = mockCard(Card.Denomination.FOUR, Card.Suit.CLUBS);
		Card mockCard4 = mockCard(Card.Denomination.FIVE, Card.Suit.CLUBS);
		Card mockCard5 = mockCard(Card.Denomination.SIX, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard5);
		sut.dealCard(mockCard3);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard4);
		sut.dealCard(mockCard2);
		sut.sortByDenomination();
		
		ArrayList<Card> compareList = new ArrayList<>();
		compareList.add(mockCard1);
		compareList.add(mockCard2);
		compareList.add(mockCard3);
		compareList.add(mockCard4);
		compareList.add(mockCard5);
		
		int i = 0;
		for(Card c : sut.getHand()) {
			assertSame(c, compareList.get(i));
			i ++;
		}
	}
	
	@Test
	public void shouldCountAceAsHighestByDefault() {
		Player sut = new Player(name);
		
		Card mockCard1 = mockCard(Card.Denomination.ACE, Card.Suit.CLUBS);
		Card mockCard2 = mockCard(Card.Denomination.KING, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		
		sut.sortByDenomination();
		
		ArrayList<Card> compareList = new ArrayList<>();
		compareList.add(mockCard2);
		compareList.add(mockCard1);

		int i = 0;
		for(Card c : sut.getHand()) {
			assertSame(c, compareList.get(i));
			i ++;
		}
	}
	
	@Test
	public void shouldNotThrowExceptionOnEmptyHand() {
		Player sut = new Player(name);
		
		sut.getScore();
	}
	
	private Card mockAoS() {
		return mockCard(Card.Denomination.ACE, Card.Suit.SPADES);
	}
	
	private Card mock7oH() {
		return mockCard(Card.Denomination.SEVEN, Card.Suit.HEARTS);
	}
	
	private Card mockCard(Card.Denomination denomination, Card.Suit suit) {
		Card mock = mock(Card.class);
		when(mock.getDenomination()).thenReturn(denomination);
		when(mock.getSuit()).thenReturn(suit);
		
		return mock;
	}
	
	private Card mockAndDealCard(Object sutObj, Card.Denomination denomination, Card.Suit suit) {
		Card c = mockCard(denomination, suit);
		if(sutObj instanceof Player) {
			Player sut = (Player) sutObj;
			sut.dealCard(c);
		}
		return c;
	}
}
