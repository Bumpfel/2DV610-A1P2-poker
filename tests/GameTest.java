package tests;

import model.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.InOrder;

public class GameTest {

	private Player mockPlayer;
	private Deck mockDeck;
	private Game sutSpy;
	
	@Test
	public void newGameShouldDealCardsToPlayer() {
		setUp();

		sutSpy.newGame();
		verify(mockPlayer, times(sutSpy.CARDS_TO_DEAL)).dealCard(any());
	}

	@Test
	public void shouldDealFromSameDeck() {
		setUp();
		
		sutSpy.newGame();
		
		verify(mockDeck, times(sutSpy.CARDS_TO_DEAL)).getTopCard();
	}
	
	@Test
	public void shouldShuffleDeckOnNewGame() {
		setUp();
		
		sutSpy.newGame();
		verify(mockDeck).shuffle();
	}
	
	@Test
	public void shouldShuffleBeforeDealingCardsOnNewGame() {
		setUp();
		
		sutSpy.newGame();
		
		InOrder order = inOrder(mockDeck, mockPlayer);
		
		order.verify(mockDeck).shuffle();
		order.verify(mockPlayer).dealCard(any());
	}
	
	@Test
	public void shouldResetDeckOnNewGame() {
		setUp();
		
		sutSpy.newGame();
		verify(mockDeck).reset();
	}
	
	@Test
	public void shouldResetDeckBeforeShufflingOnNewGame() {
		setUp();
		
		sutSpy.newGame();
		
		InOrder order = inOrder(mockDeck);
		order.verify(mockDeck).reset();
		order.verify(mockDeck).shuffle();
	}
	
	@Test
	public void shouldResetPlayerHandsBeforeDealingOnNewGame() {
		setUp();
		
		sutSpy.newGame();
		
		verify(mockPlayer).clearHand();
		InOrder order = inOrder(mockPlayer, mockDeck);
		order.verify(mockPlayer).clearHand();
		order.verify(mockDeck).getTopCard();
	}
	
	@Test
	public void shouldPresentScore() {
		setUp();
		
		when(mockPlayer.getScore()).thenReturn(Player.Score.TWO_PAIR);
		String expected = "Two pair";
		String actual = sutSpy.presentScore(mockPlayer);
		assertEquals(expected, actual);

		when(mockPlayer.getScore()).thenReturn(Player.Score.FOUR_OF_A_KIND);
		expected = "Four of a kind";
		actual = sutSpy.presentScore(mockPlayer);
		assertEquals(expected, actual);	
	}
	
	
	@Test
	public void shouldPresentHand() {
		setUp();
				
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(mockCard(Card.Denomination.ACE, Card.Suit.SPADES));
		cards.add(mockCard(Card.Denomination.ACE, Card.Suit.HEARTS));
		cards.add(mockCard(Card.Denomination.SEVEN, Card.Suit.SPADES));
		cards.add(mockCard(Card.Denomination.SEVEN, Card.Suit.HEARTS));
		cards.add(mockCard(Card.Denomination.KING, Card.Suit.CLUBS));

		String[] playerCards = { "Ace of spades", "Ace of hearts", "Seven of spades", "Seven of hearts", "King of clubs"};
		StringBuilder expStr = new StringBuilder();
		for(int i = 0; i < cards.size(); i ++) {
			when(cards.get(i).toString()).thenReturn(playerCards[i]);
			expStr.append(cards.get(i) + "\n");
		}
		
		when(mockPlayer.getHand()).thenReturn(cards);
		String actual = sutSpy.presentHand(mockPlayer);
		String expected = expStr.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnAPlayer() {
		setUp();
				
		assertTrue(sutSpy.getWinner() instanceof Player);
	}
	
	private void setUp() {
		mockPlayer = mock(Player.class);
		mockDeck = mock(Deck.class);
		sutSpy = spy(new Game(mockPlayer, mockDeck));
	}
	
	private Card mockCard(Card.Denomination denomination, Card.Suit suit) {
		Card mock = mock(Card.class);
		when(mock.getDenomination()).thenReturn(denomination);
		when(mock.getSuit()).thenReturn(suit);
		
		return mock;
	}
	
}
