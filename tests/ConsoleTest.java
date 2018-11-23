package tests;

import model.*;
import view.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConsoleTest {

	@Test
	public void shouldCallPrintWelcomeMsg() {
		Game mockGame = mock(Game.class);
		Console sutSpy = spy(new Console(mockGame));
		
		sutSpy.play();
		
		verify(sutSpy).printWelcomeMsg();
	}
}
