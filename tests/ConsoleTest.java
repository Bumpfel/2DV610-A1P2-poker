package tests;

import model.*;
import view.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ConsoleTest {

	private Game mockGame;
	private Console sutSpy;
	private ConsoleWrapper cwMock;
	
	@Test
	public void shouldCallPrintWelcomeMsg() {
		setUp();
		verify(sutSpy).printWelcomeMsg();
	}
	
	@Test
	public void printWelcomeMsgShouldCall_println_WithMsgArg() {
		setUp();
		verify(cwMock).println(sutSpy.WELCOME_MSG);
	}
	
	@Test
	public void shouldPrintInstructionsOnPlay() {
		setUp();
		verify(cwMock).println(sutSpy.INSTRUCTIONS);
	}

	@Test
	public void shouldWaitForInputOnLoad() {
		setUp();
		verify(cwMock, atLeastOnce()).getInput();
	}
	
	@Test
	public void shouldStartGameIfPIsPressed() {
		setUp();
		when(cwMock.getInput()).thenReturn("p", "");
		sutSpy.play();
		
		verify(mockGame).newGame();
	}
	
	@Test
	public void shouldNotCallNewGameIfQIsPressed() {
		setUp();
		when(cwMock.getInput()).thenReturn("q");
		sutSpy.play();
		
		verify(mockGame, never()).newGame();
	}
	
	@Test 
	public void shouldNotQuitAfterOneRound() {
		setUp();
		
		when(cwMock.getInput()).thenReturn("p", "p", "");
		sutSpy.play();
		verify(mockGame, times(2)).newGame();
	}
	
	@Test
	public void shouldPresentScore() {
		setUp();
		Player mockPlayer = mock(Player.class);
		
		when(mockPlayer.getScore()).thenReturn(Player.Score.TWO_PAIR);
		String expected = "Two pair";
		String actual = sutSpy.presentScore(mockPlayer);
		assertEquals(expected, actual);

		when(mockPlayer.getScore()).thenReturn(Player.Score.FOUR_OF_A_KIND);
		expected = "Four of a kind";
		actual = sutSpy.presentScore(mockPlayer);
		assertEquals(expected, actual);	
	}
	
	private void setUp() {
		mockGame = mock(Game.class);
		// Scanner class seems to be final, so it's not mockable. Have to use a method that returns a method call from a scanner
		cwMock = mock(ConsoleWrapper.class); 
		sutSpy = spy(new Console(mockGame, cwMock));
		
		sutSpy.play();
	}
}
