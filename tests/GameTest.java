package tests;

import model.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
	public void shouldReturnAPlayer() {
		setUp();
				
		assertTrue(sutSpy.getWinner() instanceof Player);
	}

	
	@Test
	public void shouldFillUpHandOfPlayer() {
		setUp();
		
		when(mockPlayer.getSize()).thenReturn(3);
		sutSpy.fillUpHand(mockPlayer);
		verify(mockPlayer, times(2)).dealCard(any());

		mockPlayer = mock(Player.class);
		when(mockPlayer.getSize()).thenReturn(1);
		sutSpy.fillUpHand(mockPlayer);
		verify(mockPlayer, times(4)).dealCard(any());
	}
	
	@Test
	public void shouldGetPlayer() {
		setUp();
		
		Player expected = mockPlayer;
		Player actual = sutSpy.getPlayer();
		
		assertEquals(expected, actual);
	}
	
	private void setUp() {
		mockPlayer = mock(Player.class);
		mockDeck = mock(Deck.class);
		sutSpy = spy(new Game(mockPlayer, mockDeck));
	}
	
}
