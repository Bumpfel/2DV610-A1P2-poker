package tests;

import model.*;
import view.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConsoleTest {

	private Game mockGame;
	private Console sutSpy;
	
	@Test
	public void shouldCallPrintWelcomeMsg() {
		setUp();
		verify(sutSpy).printWelcomeMsg();
	}
	
	@Test
	public void printWelcomeMsgShouldCall_println_WithMsgArg() {
		setUp();
		verify(sutSpy).println(sutSpy.WELCOME_MSG);
	}
	
	@Test
	public void shouldPrintInstructionsOnPlay() {
		setUp();
		verify(sutSpy).println(sutSpy.INSTRUCTIONS);
	}

	
	private void setUp() {
		mockGame = mock(Game.class);
		sutSpy = spy(new Console(mockGame));
		
		sutSpy.play();
	}
}
