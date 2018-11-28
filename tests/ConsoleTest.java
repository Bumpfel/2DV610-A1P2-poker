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
		String actual = sutSpy.presentHand(mockPlayer, false);
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
		String hand = sutSpy.presentHand(null, false);
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
		
		ArrayList<Card> hand = makeTemplateHand();
		
		when(mockGame.getWinner()).thenReturn(mockPlayer);
		when(mockPlayer.getScore()).thenReturn(Player.Score.TWO_PAIR);
		when(mockPlayer.getHand()).thenReturn(hand);
		sutSpy.runGame();
		
		verify(cwMock).println(sutSpy.presentHand(mockGame.getWinner(), false));
	}
	
	@Test
	public void shouldClearScreen() {
		setUp();
		sutSpy.clearScreen();
		
		verify(cwMock, times(sutSpy.CLEAR_SPACES)).println("");
	}
	
	@Test
	public void shouldClearScreenAndPrintNewInstructions() {
		setUp();
		sutSpy.runGame();
		
		InOrder order = inOrder(sutSpy, cwMock, mockGame);
		order.verify(mockGame).newGame();
		order.verify(sutSpy).clearScreen();
		order.verify(cwMock).println(sutSpy.INSTRUCTIONS2);
		order.verify(mockGame).getWinner();
	}
	
	@Test
	public void shouldGiveOptionToThrowCardsBeforeEndingGame() {
		setUp();
		
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		ArrayList<Card> tempHand = new ArrayList<>();
		tempHand.add(mock(Card.class));
		when(mockPlayer.getHand()).thenReturn(tempHand);
		sutSpy.runGame();
		
		verify(mockGame, atLeastOnce()).getPlayer();
		
		InOrder order = inOrder(sutSpy, cwMock, mockGame);
		order.verify(cwMock).println(sutSpy.presentHand(mockPlayer, true));
		order.verify(cwMock).getThrowCardInput();
		order.verify(mockGame).getWinner();
	}
	
	@Test
	public void shouldLoopUntilFinished() {
		setUp();
		
		when(cwMock.getThrowCardInput()).thenReturn("", "f");
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		sutSpy.runGame();
		
		verify(cwMock, times(3)).println(sutSpy.INSTRUCTIONS2);
		verify(sutSpy, times(3)).presentHand(mockPlayer, true);
		verify(sutSpy, atLeast(2)).clearScreen();
	}
	
	@Test
	public void shouldRunThrowCardWhenEnteringANumber() {
		setUp();
		
		when(mockPlayer.getHandSize()).thenReturn(5);
		when(cwMock.getThrowCardInput()).thenReturn("5", "f");
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		sutSpy.runGame();
		
		verify(mockGame).throwCard(any(), any());
	}
	
	@Test
	public void throwCardsShouldOnlyAcceptValidInput() {
		// valid input - 1 to hand size
		setUp();
		int handSize = 5;
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		when(mockPlayer.getHandSize()).thenReturn(handSize);
		sutSpy.wantsToThrowCards("0");
		sutSpy.wantsToThrowCards("" + (handSize + 1));
		
		verify(mockGame, never()).throwCard(any(), any());
		
		sutSpy.wantsToThrowCards("" + handSize);
		sutSpy.wantsToThrowCards("1");
		verify(mockGame, times(2)).throwCard(any(), any());
	}
	
	@Test
	public void shouldThrowCorrectCard() {
		setUp();
		
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		ArrayList<Card> hand = makeTemplateHand();
		when(mockPlayer.getHand()).thenReturn(hand);
		
		sutSpy.wantsToThrowCards("2"); 
		
		verify(mockGame).throwCard(mockPlayer, hand.get(1)); // input is one higher than arraylist index
	}
	
	@Test
	public void throwCardsShouldGiveErrorMsgOnInvalidInput() {
		setUp();
		
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		when(mockPlayer.getHandSize()).thenReturn(5);
		sutSpy.wantsToThrowCards("6");
		sutSpy.wantsToThrowCards("h");
		
		verify(cwMock, times(2)).println("Invalid input");
	}
	
	@Test
	public void shouldGetNewCardsWhenFinishedThrowingCards() {
		setUp();
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		sutSpy.runGame();
		
		verify(mockGame).fillUpHand(mockPlayer);
	}

	@Test
	public void shouldPrintNumbersBeforeCardsWhenThrowing() {
		setUp();
		
		Iterable<Card> hand = makeTemplateHand();
		when(mockPlayer.getHand()).thenReturn(hand);
		String presentedHand = sutSpy.presentHand(mockPlayer, true);
		
		assertTrue(presentedHand.contains("1. "));
		assertTrue(presentedHand.contains("2. "));
		assertTrue(presentedHand.contains("3. "));
		assertTrue(presentedHand.contains("4. "));
		assertTrue(presentedHand.contains("5. "));
	}
	
	@Test
	public void shouldClearScreenAndPrintGameOverMsg() {
		setUp();
		
		when(cwMock.getThrowCardInput()).thenReturn("f");
		sutSpy.runGame();
		
		InOrder order = inOrder(sutSpy, mockGame, cwMock);
		order.verify(mockGame).fillUpHand(any());
		order.verify(sutSpy).clearScreen();
		order.verify(cwMock).print(sutSpy.GAME_OVER_MSG);
	}
	
	@Test
	public void shouldPauseAfterDisplayingInvalidInputMsg() {
		setUp();
		when(mockGame.getPlayer()).thenReturn(mockPlayer);
		when(mockPlayer.getHandSize()).thenReturn(5);
		sutSpy.wantsToThrowCards("6");

		sutSpy.wantsToThrowCards("asd");
		
		InOrder order = inOrder(cwMock);
		order.verify(cwMock).println(sutSpy.INVALID_INPUT);
		order.verify(cwMock).pause(sutSpy.PAUSE_TIME);
		
		verify(cwMock, times(2)).pause(sutSpy.PAUSE_TIME);
	}
	
	@Test
	public void shouldPresentTwoCardSwapRounds() {
		setUp();
		when(cwMock.getThrowCardInput()).thenReturn("f", "f");
		sutSpy.runGame();
		
		verify(sutSpy, times(2)).throwCards();
	}
	
	private void setUp() {
		mockGame = mock(Game.class);
		mockPlayer = mock(Player.class);
		// Scanner class seems to be final, so it's not mockable. Have to use a method that returns a method call from a scanner
		cwMock = mock(ConsoleWrapper.class); 
		when(cwMock.getThrowCardInput()).thenReturn("f");
		sutSpy = spy(new Console(mockGame, cwMock));
	}
	
	private Card mockCard(Card.Denomination denomination, Card.Suit suit) {
		Card mock = mock(Card.class);
		when(mock.getDenomination()).thenReturn(denomination);
		when(mock.getSuit()).thenReturn(suit);
		
		return mock;
	}
	
	private ArrayList<Card> makeTemplateHand() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(mockCard(Card.Denomination.ACE, Card.Suit.SPADES));
		hand.add(mockCard(Card.Denomination.ACE, Card.Suit.HEARTS));
		hand.add(mockCard(Card.Denomination.SEVEN, Card.Suit.SPADES));
		hand.add(mockCard(Card.Denomination.SEVEN, Card.Suit.HEARTS));
		hand.add(mockCard(Card.Denomination.KING, Card.Suit.CLUBS));
		
		when(mockPlayer.getHandSize()).thenReturn(5);
		
		return hand;

	}
}
