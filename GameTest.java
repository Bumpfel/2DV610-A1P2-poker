import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.Test;

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
}
