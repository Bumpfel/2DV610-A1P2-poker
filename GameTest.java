import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.InOrder;

public class GameTest {

	@Test
	public void newGameShouldDealCardsToPlayer() {
		Player mockPlayer = mock(Player.class);
		Deck mockDeck = mock(Deck.class);
		Game sutSpy = spy(new Game(mockPlayer, mockDeck));

		sutSpy.newGame();
	
		verify(mockPlayer, times(sutSpy.CARDS_TO_DEAL)).dealCard(any());
	}

	@Test
	public void shouldDealFromSameDeck() {
		Player mockPlayer = mock(Player.class);
		Deck mockDeck = mock(Deck.class);
		Game sutSpy = spy(new Game(mockPlayer, mockDeck));
		
		sutSpy.newGame();
		
		verify(mockDeck, times(sutSpy.CARDS_TO_DEAL)).getTopCard();
	}
	
	@Test
	public void shouldShuffleDeckOnNewGame() {
		Player mockPlayer = mock(Player.class);
		Deck mockDeck = mock(Deck.class);
		Game sutSpy = spy(new Game(mockPlayer, mockDeck));
		
		sutSpy.newGame();
		
		verify(mockDeck).shuffle();
	}
	
	
	@Test
	public void shouldShuffleBeforeDealingCardsOnNewGame() {
		Player mockPlayer = mock(Player.class);
		Deck mockDeck = mock(Deck.class);
		Game sutSpy = spy(new Game(mockPlayer, mockDeck));
		
		sutSpy.newGame();
		
		InOrder order = inOrder(mockDeck, mockPlayer);
		
		order.verify(mockDeck).shuffle();
		order.verify(mockPlayer).dealCard(any());
	}
	
}
