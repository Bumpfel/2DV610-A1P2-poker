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
	
	@Test
	public void printWelcomeMsgShouldCall_println_WithMsgArg() {
		Game mockGame = mock(Game.class);
		Console sutSpy = spy(new Console(mockGame));
		
		sutSpy.play();

		verify(sutSpy).println(sutSpy.WELCOME_MSG);
	}

}
