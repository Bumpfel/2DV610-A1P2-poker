package tests;

import model.*;
import view.*;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class ConsoleTest {

	private Player mockPlayer;
	private Game mockGame;
	private Console sutSpy;
	private ConsoleWrapper cwMock;
	
	@Test
	public void shouldCallPrintWelcomeMsg() {
		setUp();
		sutSpy.play();
		verify(sutSpy).printWelcomeMsg();
	}
	
	@Test
	public void printWelcomeMsgShouldCall_println_WithMsgArg() {
		setUp();
		sutSpy.play();
		verify(cwMock).println(sutSpy.WELCOME_MSG);
	}
	
	@Test
	public void shouldPrintInstructionsOnPlay() {
		setUp();
		sutSpy.play();
		verify(cwMock).println(sutSpy.INSTRUCTIONS);
	}

	@Test
	public void shouldWaitForInputOnLoad() {
		setUp();
		sutSpy.play();
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
	public void gameShouldCallGetWinnerAfterNewGame() {
		setUp();
		
		sutSpy.runGame();
		
		InOrder order = inOrder(mockGame);
		order.verify(mockGame).newGame();
		order.verify(mockGame).getWinner();
	}
	
	@Test
	public void getWinnerShouldBeCalledEveryGame() {
		setUp();
		
		sutSpy.runGame();
		verify(mockGame, atLeastOnce()).getWinner();
	}

	@Test
	public void shouldReturnNullScoreOnNullArg() {
		setUp();
		
		sutSpy.runGame();
		String score = sutSpy.presentScore(null);
		assertNull(score);
	}
	
	@Test
	public void shouldReturnNullHandOnNullArg() {
		setUp();
		
		sutSpy.runGame();
		String hand = sutSpy.presentHand(null);
		assertNull(hand);
	}
	
	@Test
	public void shouldPresentWinningScoreOnRunGame() {
		setUp();
		
		when(mockGame.getWinner()).thenReturn(mockPlayer);
		when(mockPlayer.getScore()).thenReturn(Player.Score.TWO_PAIR);
		sutSpy.runGame();
		
		verify(cwMock).println(sutSpy.presentScore(mockPlayer));
	}
	
	@Test
	public void shouldPresentWinningHandOnRunGame() {
		setUp();
		
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(mockCard(Card.Denomination.ACE, Card.Suit.SPADES));
		hand.add(mockCard(Card.Denomination.ACE, Card.Suit.HEARTS));
		hand.add(mockCard(Card.Denomination.SEVEN, Card.Suit.SPADES));
		hand.add(mockCard(Card.Denomination.SEVEN, Card.Suit.HEARTS));
		hand.add(mockCard(Card.Denomination.KING, Card.Suit.CLUBS));
		
		when(mockGame.getWinner()).thenReturn(mockPlayer);
		when(mockPlayer.getScore()).thenReturn(Player.Score.TWO_PAIR);
		when(mockPlayer.getHand()).thenReturn(hand);
		sutSpy.runGame();
		
		verify(cwMock).println(sutSpy.presentHand(mockGame.getWinner()));
	}
	
	@Test
	public void shouldClearScreen() {
		setUp();
		
		sutSpy.clearScreen();
		
		verify(cwMock, times(sutSpy.CLEAR_SPACES)).println("");
	}
	
	private void setUp() {
		mockGame = mock(Game.class);
		mockPlayer = mock(Player.class);
		// Scanner class seems to be final, so it's not mockable. Have to use a method that returns a method call from a scanner
		cwMock = mock(ConsoleWrapper.class); 
		sutSpy = spy(new Console(mockGame, cwMock));
	}
	
	private Card mockCard(Card.Denomination denomination, Card.Suit suit) {
		Card mock = mock(Card.class);
		when(mock.getDenomination()).thenReturn(denomination);
		when(mock.getSuit()).thenReturn(suit);
		
		return mock;
	}
}
