import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class GameTest {

	@Test
	public void newGameShouldDealCardsToPlayer() {
		Player mockPlayer = mock(Player.class);
		Game sutSpy = spy(new Game(mockPlayer));

		sutSpy.newGame();
	
		verify(mockPlayer, times(sutSpy.CARDS_TO_DEAL)).dealCard(any(Card.class));
	}

}
