package tests;

import model.*;
import view.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConsoleTest {

	@Test
	public void shouldPrintWelcomeMsg() {
		Game mockGame = mock(Game.class);
		Console sut = new Console(mockGame);
		Console sutSpy = spy(sut);
		
		sutSpy.play();
		
		verify(sutSpy).printWelcomeMsg();
	}
}
