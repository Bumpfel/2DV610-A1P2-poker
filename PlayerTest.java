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
	
	@Test
	public void shouldBeAbleToClearHand() {
		sut = new Player();
		
		Card mockCard = mock(Card.class);
		
		sut.dealCard(mockCard);
		sut.dealCard(mockCard);
		sut.clearHand();
		
		int expected = 0;
		int actual = sut.getSize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDeterminePair() {
		sut = new Player();
		
		Card mockCard = mockAoS();
		Card mockCard3 = mockCard(Card.Denomination.THREE, Card.Suit.CLUBS);
		Card mockCard4 = mockCard(Card.Denomination.FOUR, Card.Suit.CLUBS);
		Card mockCard5 = mockCard(Card.Denomination.FIVE, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard);
		sut.dealCard(mockCard); // dealing the same card won't happen when there is a real deck, so I don't need to test for it
		sut.dealCard(mockCard3);
		sut.dealCard(mockCard4);
		sut.dealCard(mockCard5);
		
		Player.Score expected = Player.Score.PAIR;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineHighCard() {
		sut = new Player();

		Card mockCard = mockAoS();
		Card mockCard2 = mockCard(Card.Denomination.TWO, Card.Suit.CLUBS);
		Card mockCard3 = mockCard(Card.Denomination.THREE, Card.Suit.CLUBS);
		Card mockCard4 = mockCard(Card.Denomination.JACK, Card.Suit.CLUBS);
		Card mockCard5 = mockCard(Card.Denomination.FIVE, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard3);
		sut.dealCard(mockCard4);
		sut.dealCard(mockCard5);

		Player.Score expected = Player.Score.HIGH_CARD;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineThreeOfAKind() {
		sut = new Player();
		
		Card mockCard = mockAoS();
		Card mockCard4 = mockCard(Card.Denomination.TWO, Card.Suit.CLUBS);
		Card mockCard5 = mockCard(Card.Denomination.THREE, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard);
		sut.dealCard(mockCard);
		sut.dealCard(mockCard);
		sut.dealCard(mockCard4);
		sut.dealCard(mockCard5);
		
		Player.Score expected = Player.Score.THREE_OF_A_KIND;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineTwoPair() {
		sut = new Player();
		
		Card mockCard1 = mockAoS();
		Card mockCard2 = mock7oH();
		Card mockCard5 = mockCard(Card.Denomination.FIVE, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard5);
		
		Player.Score actual = sut.getScore();
		Player.Score expected = Player.Score.TWO_PAIR;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineStraight() {
		sut = new Player();
		
		Card mockCard1 = mockAoS();
		Card mockCard2 = mockCard(Card.Denomination.TWO, Card.Suit.CLUBS);
		Card mockCard3 = mockCard(Card.Denomination.THREE, Card.Suit.CLUBS);
		Card mockCard4 = mockCard(Card.Denomination.FOUR, Card.Suit.CLUBS);
		Card mockCard5 = mockCard(Card.Denomination.FIVE, Card.Suit.CLUBS);
		
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard3);
		sut.dealCard(mockCard4);
		sut.dealCard(mockCard5);
		
		Player.Score expected = Player.Score.STRAIGHT;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineFlush() {
		sut = new Player();
		
		Card mockCard1 = mockAoS();
		Card mockCard2 = mockCard(Card.Denomination.TWO, Card.Suit.SPADES);
		Card mockCard3 = mockCard(Card.Denomination.JACK, Card.Suit.SPADES);
		Card mockCard4 = mockCard(Card.Denomination.FOUR, Card.Suit.SPADES);
		Card mockCard5 = mockCard(Card.Denomination.FIVE, Card.Suit.SPADES);
		
		sut.dealCard(mockCard1);
		sut.dealCard(mockCard2);
		sut.dealCard(mockCard3);
		sut.dealCard(mockCard4);
		sut.dealCard(mockCard5);
		
		Player.Score expected = Player.Score.FLUSH;
		Player.Score actual = sut.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldDetermineFullHouse() {
		sut = new Player();
		
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
	public void shouldReturnNullIfHandIncomplete() {
		sut = new Player();
		
		sut.dealCard(mockAoS());
		Player.Score actual = sut.getScore();
		
		assertNull(actual);
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
	
}
